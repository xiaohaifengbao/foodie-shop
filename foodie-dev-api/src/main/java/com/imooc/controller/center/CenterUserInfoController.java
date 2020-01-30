package com.imooc.controller.center;

import com.imooc.config.ImageConfig;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.DateUtil;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname CenterUserInfoController
 * @Description TODO
 * @Date 2020/1/28 21:01
 * @Created by 于明灏
 */
@Api(value = "用户信息", tags = {"用户信息增删改查"})
@RestController
@Slf4j
@RequestMapping("/userInfo")
public class CenterUserInfoController {

    @Autowired
    private CenterUserService centerUserService;
    @Autowired
    private ImageConfig imageConfig;
    @Resource
    private UsersMapper usersMapper;

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(
            @ApiParam(name = "userId", value = "用户ID", example = "191108GST0BS82K4")
            @RequestParam("userId") String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (result.hasErrors()) { // 校验不通过
            Map<String, String> errorMsg = this.getErrorMsg(result);
            return IMOOCJSONResult.errorMap(errorMsg);
        }

        // 获取更新后的用户信息
        Users user = centerUserService.updateUserInfo(centerUserBO, userId);
        // 同步更新cookie中的用户信息
        CookieUtils.setCookie(request, response, "users", JsonUtils.objectToJson(user), true);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "修改用户头像", notes = "修改用户头像", httpMethod = "POST")
    @PostMapping("/uploadFace")
    public IMOOCJSONResult uploadFace(
            @ApiParam(name = "userId", value = "用户ID", example = "191108GST0BS82K4")
            @RequestParam("userId") String userId,
            @ApiParam(name = "multipartFile", value = "用户头像")
            @RequestParam("file") MultipartFile multipartFile) {

        FileOutputStream outputStream = null;

        if(multipartFile != null) {
            try {
                // 获取上传图片名称
                String originalFilename = multipartFile.getOriginalFilename();
                if(!StringUtils.isBlank(originalFilename)) {
                    // 获取文件后缀名
                    String[] fileNameArrays = originalFilename.split("\\.");
                    String suffixName = fileNameArrays[fileNameArrays.length - 1];
                    // 图片格式校验
                    if(!"jpeg".equals(suffixName)
                            && !"jpg".equals(suffixName)
                            && !"png".equals(suffixName)
                            && !"gif".equals(suffixName)
                            ) {
                        return IMOOCJSONResult.errorMsg("图片格式不正确");
                    }

                    // 获取通用url
                    String prefixPath = imageConfig.getUploadPath();
                    // 文件名
                    String newDateTime = DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);
                    // 拼接完整上传url(覆盖式)
                    String finalName = prefixPath + File.separator + userId + File.separator
                            + "face-" + userId + "." + suffixName;

                    File file = new File(finalName);
                    if (file != null) {
                        if(file.getParentFile() != null) {
                            // 创建上级文件夹
                            file.getParentFile().mkdirs();
                            // 创建输出流
                            outputStream = new FileOutputStream(file);
                            // 获取文件的输入流
                            InputStream inputStream = multipartFile.getInputStream();
                            // 写入目录中
                            IOUtils.copy(inputStream, outputStream);
                            // 写入数据库中
                                // 获取url前缀
                            String prefixUrl = imageConfig.getPrefixUrl();
                            // 获取完整url -> 时间戳获取最新的图片(缓存)
                            String finalUrl = prefixUrl + "/" + userId + "/" + "face-" +userId + "." + suffixName
                                    + "?timestamp=" + newDateTime;
                            // 入库
                            Users users = new Users();
                            users.setId(userId);
                            users.setFace(finalUrl);
                            users.setUpdatedTime(new Date());
                            usersMapper.updateByPrimaryKeySelective(users);

                            return IMOOCJSONResult.ok();
                        }
                    }
                }
            } catch (IOException e) {
                log.error("上传发生错误", e.getMessage());
            } finally {
                try {
                    if(outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (IOException e) {
                    log.error("uploadFace关闭资源发生错误", e.getMessage());
                }
            }
        }else {
            return IMOOCJSONResult.errorMsg("文件不能为空");
        }

        return IMOOCJSONResult.errorMsg("上传图片有误");
    }

    private Map<String, String> getErrorMsg(BindingResult result) {
        HashMap<String, String> returnMap = new HashMap<>();

        List<FieldError> errorList = result.getFieldErrors();
        errorList.forEach(errorModel -> {
            returnMap.put(errorModel.getField(), errorModel.getDefaultMessage());
        });
        return returnMap;
    }
}
