package com.guo.service;

import com.guo.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author guo
 * @Date 2023 03 31 16 46
 **/
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
