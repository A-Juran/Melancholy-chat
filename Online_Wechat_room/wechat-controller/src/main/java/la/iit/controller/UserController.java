package la.iit.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.response.AjaxResult;
import la.iit.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/login")
    @ApiOperationSupport(author = "21171326@qq.com")
    @Operation(summary  = "用户登录")
    public AjaxResult login(String code){
        //http send msg
        //login wx
        log.info("接收的code{}",code);
       String content = OkHttpUtils.builder()
                .url("http://www.baidu.com")
                .get().async();

        log.info("请求成功{}",content);
        return null;
    }
}
