package la.iit.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.annotation.SysLogin;
import la.iit.annotation.VisitLimit;
import la.iit.entity.domain.OwUser;
import la.iit.entity.dto.UserDTO;
import la.iit.entity.vo.UserInfoVO;
import la.iit.response.AjaxResult;
import la.iit.service.UserService;
import la.iit.utils.GlobalParamsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static la.iit.common.Constant.REGISTER_FAILED;
import static la.iit.common.Constant.REGISTER_SUCCESS;

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
    private UserService userService;

    private GlobalParamsUtils globalParamsUtils;


    public UserController(UserService userService,
                          GlobalParamsUtils globalParamsUtils) {
        this.userService = userService;
        this.globalParamsUtils = globalParamsUtils;

    }
    @GetMapping("/captcha")
    @Operation(summary = "获取验证码")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult captcha(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        HashMap<String,Object> captchaMap
                = userService.captcha();
        // 将key和base64返回给前端
        return AjaxResult.success().put("key", captchaMap.get("key"))
                .put("image",captchaMap.get("image"));
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult login(@RequestBody @Validated(UserDTO
            .UserLogin.class)
                            UserDTO userDTO) {
        String token = null;
        UserInfoVO userInfoVO =
                UserInfoVO.builder();
        try {
            token = userService.login(userDTO.getUsername(),
                    userDTO.getPassword());
            OwUser currentUser =
                    globalParamsUtils.getCurrentUser();
            BeanUtils.copyProperties(currentUser, userInfoVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success()
                .put("token", token)
                .put("currentUser", userInfoVO);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult register(@RequestBody
                               @Validated(UserDTO.UserRegister.class)
                               UserDTO userDTO) {
        try {
            userService.register(userDTO);
        } catch (Exception e) {
            return AjaxResult.failed(REGISTER_FAILED.value());
        }
        return AjaxResult.success(REGISTER_SUCCESS.value());
    }
}
