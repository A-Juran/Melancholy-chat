package la.iit.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.annotation.SysLogin;
import la.iit.annotation.VisitLimit;
import la.iit.config.WxAppIdConfig;
import la.iit.dto.UserDTO;
import la.iit.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JuRan
 * @date 2023/1/20
 * 用户登录
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "系统操作")
@ApiSupport(author = "21171326@qq.com")
public class UserController {

    private WxAppIdConfig wxAppIdConfig;

    public UserController(WxAppIdConfig appIdConfig){
        this.wxAppIdConfig = appIdConfig;
    }

    @PostMapping("/login")
    @ApiOperationSupport(author = "21171326@qq.com")
    @Operation(summary = "用户登录")
    @SysLogin
    @VisitLimit(limit = 1,sec = 1)
    public AjaxResult login(@RequestBody @Validated(UserDTO.UserLogin.class) UserDTO userDTO) {
        log.info("接收的code{}", userDTO.getCode());
//        String content = OkHttpUtils.builder()
//                .addParams("appid", wxAppIdConfig.getAppid())
//                .addParams("secret", wxAppIdConfig.getSecret())
//                .addParams("js_code", userDTO.getCode())
//                .addParams("grant_type", "authorization_code")
//                .url("https://api.weixin.qq.com/sns/jscode2session")
//                .get().async();
//        log.info("请求成功----->{}", content);
        return null;
    }
}
