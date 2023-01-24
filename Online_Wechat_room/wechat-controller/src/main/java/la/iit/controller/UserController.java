package la.iit.controller;


import la.iit.response.AjaxResult;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JuRan
 * @date 2023/1/20
 * 用户登录
 */
@RestController("/user")
public class UserController {

    @GetMapping("/login")
    public AjaxResult login(@NotNull String code){
        //http send msg
        OkHttpClient okHttpClient = new OkHttpClient();

        return null;
    }
}
