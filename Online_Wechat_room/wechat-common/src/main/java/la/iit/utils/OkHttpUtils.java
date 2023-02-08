package la.iit.utils;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author JuRan
 * @date 2023/1/21
 * okHttpUtils
 */
public class OkHttpUtils {
    //okHttp对象初始化。
    private static volatile OkHttpClient  okHttpClient = null;
    //todo 认识该对象。
    private static volatile Semaphore semaphore = null;
    //请求头数据。
    private Map<String,String> headerMap;
    //请求参数。
    private Map<String,String> paramMap;
    //请求链接。
    private String url;
    //请求构造对象。
    private Request.Builder request;

    /**
     * 初始化okHttpClient，并允许https访问。
     */
    private OkHttpUtils(){
        if (okHttpClient == null){
            synchronized (OkHttpClient.class){
                if (okHttpClient == null){
                    //套字节。
                    TrustManager[] trustManagers = buildTrustManagers();
                    //初始化okHttp
                    okHttpClient = new OkHttpClient().newBuilder()
                            //读写超时时间、链接超时时间。
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(20,TimeUnit.SECONDS)
                            .readTimeout(20,TimeUnit.SECONDS)
                            //套子接通信工厂。
                            .sslSocketFactory(createSSLSocketFactory(trustManagers), (X509TrustManager) trustManagers[0])
                            //主机名验证
                            .hostnameVerifier((hostName,session) -> true)
                            //重试失败链接超时。
                            .retryOnConnectionFailure(true)
                            .build();
                    //设置请求头。
                    addHeader("User-Agent",
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
                }
            }
        }
    }
    public void async(ICallBack iCallBack){
        setHeader(request);
        okHttpClient.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                iCallBack.onFailure(call,e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                assert response.body() != null;
                iCallBack.onSuccessful(call,response.body().string());
            }
        });
    }

    /**
     * 异步请求
     * @return
     */
    public String async(){
        StringBuilder buffer  = new StringBuilder("");
        setHeader(request);
        okHttpClient.newCall(request.build()).enqueue(new Callback() {
            //失败返回
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                buffer.append("请求出错：").append(e.getMessage());
            }
            //成功返回
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                assert response.body() != null;
                buffer.append(response.body().string());
                //释放
                semaphore.release();
            }
        });
        // todo ?
        try {
            getSemaphoreInstance().acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 同步请求
     *
     * @return
     */
    public String sync(){
        //设置请求头
        setHeader(request);
        try {
            Response response = okHttpClient.newCall(request.build()).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "请求失败：" +  e.getMessage();
        }
    }

    /**
     * post 请求 isJsonPost true等于json的方式提交数据，类似postman里post方法的raw
     *
     * false等于普通的表单提交
     *
     * @return
     */
    public OkHttpUtils post(boolean isJsonPost ){
        RequestBody requestBody;
        if (isJsonPost){
            String json = "";
            if (paramMap != null){
                json = JSON.toJSONString(paramMap);
            }
            requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
        }else {
            FormBody.Builder formBody = new FormBody.Builder();
            if (paramMap != null){
                paramMap.forEach(formBody::add);
            }
            requestBody =  formBody.build();
        }

        request = new Request.Builder().post(requestBody).url(url);

        return this;
    }


    /**
     * 初始化get 方法。
     *
     * @return
     */
    public OkHttpUtils get(){
        //builder request object。
        request = new Request.Builder().get();
        //setting request params.
        StringBuilder urlBuilder = new StringBuilder();
        if (paramMap != null){
            urlBuilder.append("?");
            //对其map进行遍历进行数据拼接。
            try {
                for (Map.Entry<String,String> entry:paramMap.entrySet()){
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), String.valueOf(StandardCharsets.UTF_8)))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), String.valueOf(StandardCharsets.UTF_8)))
                            .append("&");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            //删除最后一个字符&
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        request.url(urlBuilder.toString());
        return this;
    }

    /**
     * 设置request 为请求头。
     *
     * @param request
     */
    private void setHeader(Request.Builder request){
        if (headerMap != null){
            try {
                for(Map.Entry<String,String> entry : headerMap.entrySet()){
                    request.addHeader(entry.getKey(),entry.getValue());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 添加请求参数
     * @param key   参数名
     * @param value 参数值
     * @return this
     */
    public OkHttpUtils addParams(String key,String value){

        if (paramMap == null){
            paramMap = new LinkedHashMap<>(16);
        }

        paramMap.put(key,value);
        return this;
    }

    /**
     * 设置url
     * @param url 请求url地址。
     * @return this
     */
    public OkHttpUtils url(String url){
        this.url = url;
        return this;
    }

    /**
     * create okHttpUtils object;
     * @return
     */
    public static OkHttpUtils builder(){
        return new OkHttpUtils();
    }


    /**
     * 用于异步请求时，控制访问进程数，返回结果。
     *
     * @return
     */
    private static Semaphore getSemaphoreInstance(){
        //控制异步时只能1个线程同时访问。
        synchronized(OkHttpClient.class){
            if (semaphore == null){
                semaphore = new Semaphore(0);
            }
        }

        return semaphore;
    }


    /**
     * 添加请求头
     *
     * @param key   参数名
     * @param value 参数值
     * @return
     */
    public OkHttpUtils addHeader(String key, String value) {
        if (headerMap == null) {
            headerMap = new LinkedHashMap<>(16);
        }
        headerMap.put(key, value);
        return this;
    }


    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    /**
     * 自定义个一个接口回调
     */
    public interface ICallBack{
        void onSuccessful(Call call,String msg);
        void onFailure(Call call,String errMsg);
    }
}
