package cn.hsmxg1204.test.controller;

import cn.hsmxg1204.core.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-08 17:13
 */
@RestController
@RequestMapping(value = "/upload")
@Api(value = "上传文件")
public class UploadFileController {
    public Result uploadFile(@RequestParam("path") String path, @RequestParam("file")MultipartFile file){
        return null;
    }
}
