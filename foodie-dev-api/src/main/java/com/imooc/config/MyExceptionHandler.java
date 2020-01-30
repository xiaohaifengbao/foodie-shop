package com.imooc.config;

import com.imooc.utils.IMOOCJSONResult;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @Classname MyExceptionHandler
 * @Description TODO
 * @Date 2020/1/30 12:22
 * @Created by 于明灏
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public IMOOCJSONResult errorHandler(MaxUploadSizeExceededException e) {
        return IMOOCJSONResult.errorMsg("图片大小不能超过1M");
    }
}
