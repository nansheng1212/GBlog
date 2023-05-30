package com.guo.controller;

import com.guo.annotation.SystemLog;
import com.guo.domain.ResponseResult;
import com.guo.service.UploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author guo
 * @Date 2023 03 31 16 40
 **/

@RestController
@Api(tags = "更新头像",description = "更新头像相关接口")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @SystemLog(BusinessName = "更新头像")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
