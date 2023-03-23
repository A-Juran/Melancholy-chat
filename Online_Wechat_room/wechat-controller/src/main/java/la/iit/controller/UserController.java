package la.iit.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.annotation.SysLogin;
import la.iit.annotation.VisitLimit;
import la.iit.entity.dto.UserDTO;
import la.iit.entity.domain.OwUser;
import la.iit.response.AjaxResult;
import la.iit.service.UserService;
import la.iit.entity.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static la.iit.common.Constant.USER_INFO_COMPLETE_BEGIN;
import static la.iit.common.Constant.USER_INFO_COMPLETE_OK;

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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getCurrentUserInfo")
    @Operation(summary = "获取当前登录用户信息")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult getCurrentUserInfo() {
        UserInfoVO userBasicVO =
                UserInfoVO.builder();
        try {
            OwUser currentUserInfo = userService.getCurrentUserInfo();
            BeanUtils.copyProperties(currentUserInfo, userBasicVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success().put("currentUserInfo", userBasicVO);
    }

    @PutMapping("/updateUserInfo")
    @Operation(summary = "更新用户信息")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult updateUserInfo(@RequestBody @Validated(UserDTO.UserInfoUpdate.class)
                                     UserDTO userDTO) {
        try {
            userService.updateUserInfo(userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success();
    }

    @GetMapping("/getUserInfoPerStatus")
    @Operation(summary = "获取用户信息完善状态")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult getUserInfoPerStatus() {
        boolean userInfoImproveStatus = false;
        try {
            userInfoImproveStatus = userService.getUserInfoImproveStatus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userInfoImproveStatus ?
                AjaxResult.success(USER_INFO_COMPLETE_OK.value(), true) :
                AjaxResult.failed(USER_INFO_COMPLETE_BEGIN.value(), false);

    }

    @PostMapping("/login")
    @Operation(summary = "用户登录/注册")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult login(@RequestBody @Validated(UserDTO.UserLogin.class)
                            UserDTO userDTO) {
        String token = null;
        try {
            token = userService.saveUserInfo(userDTO.getCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success().put("token", token);
    }
}
