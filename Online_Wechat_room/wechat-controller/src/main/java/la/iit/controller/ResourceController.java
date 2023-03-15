package la.iit.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author JuRan
 * @date 2023/3/15
 */
@RestController
@RequestMapping("/resource")
@Slf4j
@Tag(name = "资源管理")
public class ResourceController {

    @PostMapping("/uploadImages")
    public AjaxResult uploadImages(MultipartFile[] multipartFiles){
        return null;
    }
}
