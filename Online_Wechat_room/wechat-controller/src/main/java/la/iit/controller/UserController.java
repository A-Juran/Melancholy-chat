package la.iit.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.annotation.SysLogin;
import la.iit.annotation.VisitLimit;
import la.iit.config.WxAppIdConfig;
import la.iit.dto.UserDTO;
import la.iit.response.AjaxResult;
import la.iit.service.UserService;
import la.iit.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static la.iit.common.Constant.REQUEST_EXCEPTION;

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
    private UserService userService;

    public UserController(WxAppIdConfig appIdConfig,
                          UserService userService) {
        this.wxAppIdConfig = appIdConfig;
        this.userService = userService;
    }

    @PutMapping("/updateUserInfo")
    @Operation(summary = "更新用户信息")
    @SysLogin
    @VisitLimit(limit = 1, sec = 1)
    public AjaxResult updateUserInfo(@RequestBody @Validated(UserDTO.UserInfoUpdate.class) UserDTO userDTO) {
        try {
            userService.updateUserInfo(userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success("修改成功");
    }

    @GetMapping("/getUserInfoPerStatus")
    @Operation(summary = "获取用户信息完善状态")
    @SysLogin
    @VisitLimit(limit = 1, sec = 1)
    public AjaxResult getUserInfoPerStatus() {
        boolean userInfoImproveStatus = false;
        try {
            userInfoImproveStatus = userService.getUserInfoImproveStatus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userInfoImproveStatus ?
                AjaxResult.success("perfectionStatus", true) :
                AjaxResult.error("perfectionStatus", false);

    }

    @PostMapping("/login")
    @Operation(summary = "用户登录/注册")
    @SysLogin
    @VisitLimit(limit = 1, sec = 1)
    public AjaxResult login(@RequestBody @Validated(UserDTO.UserLogin.class) UserDTO userDTO) {
        JSONObject wxParams = getWxParams(userDTO);
        String token =
                userService.saveUserInfo(wxParams.get("openid").toString(),
                        wxParams.get("unionid").toString());
        return AjaxResult.success("token", token);
    }

    //getOPenId and Other
    public JSONObject getWxParams(UserDTO userDTO) {
        String reponseContent = null;
        try {
            reponseContent = OkHttpUtils.builder()
                    .addParams("appid", wxAppIdConfig.getAppid())
                    .addParams("secret", wxAppIdConfig.getSecret())
                    .addParams("js_code", userDTO.getCode())
                    .addParams("grant_type", "authorization_code")
                    .url("https://api.weixin.qq.com/sns/jscode2session")
                    .get().async();
        } catch (Exception e) {
            throw new RuntimeException(REQUEST_EXCEPTION.value());
        }
        return JSONObject.parseObject(reponseContent);
    }
}
