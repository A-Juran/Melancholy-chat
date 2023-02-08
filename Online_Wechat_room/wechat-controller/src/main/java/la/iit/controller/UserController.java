package la.iit.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.response.AjaxResult;
import la.iit.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JuRan
 * @date 2023/1/20
 * 用户登录
 */
@RestController("/user")
@Slf4j
@Tag(name = "系统操作")
@ApiSupport(author = "21171326@qq.com")
public class UserController {

    @GetMapping("/login")
    @ApiOperationSupport(author = "21171326@qq.com")
    @Operation(summary  = "用户登录")
    public AjaxResult login(@NotNull String code){
        //http send msg
        //login wx
        log.info("接收的code{}",code);
        OkHttpUtils.builder()
                .url("https://www.baidu.com").async(new OkHttpUtils.ICallBack() {
                    @Override
                    public void onSuccessful(Call call, String msg) {
                        log.info("请求成功后得到的信息{}",call.toString());
                    }
                    @Override
                    public void onFailure(Call call, String errMsg) {
                        log.info("请求fail后得到的信息{}",call.toString());
                    }
                });
        return null;
    }
}
