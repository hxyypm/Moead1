package com.jcohy.moead.controller;


import com.jcohy.date.DateUtils;
import com.jcohy.moead.common.JsonResult;
import com.jcohy.moead.model.Resource;
import com.jcohy.moead.model.User;
import com.jcohy.moead.service.ResourceService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

/**
 * Copyright  : 2015-2033 Beijing Startimes Communication & Network Technology Co.Ltd
 * Created by jiac on 2017/12/27 16:04.
 * ClassName  : UploadController
 * Description  :
 */
@Controller
public class UploadController {

    @Autowired
    private ResourceService resourceService;

    @Value("${file.path}")
    private String filePath;

    @PostMapping("/upload")
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        //上传到服务器的地址
        File upload = null;
        //上传到服务器的文件名
        String fileName = file.getOriginalFilename();
        StringBuffer url = request.getRequestURL();
        System.out.println("requestUrl----"+url.toString());
        upload = new File(filePath);
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(upload+File.separator+fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        String downloadUrl = StringUtils.replace(url.toString(), "upload", "download");
        System.out.println("replaceUrl-----"+downloadUrl);
        Resource resource = save(fileName, downloadUrl, request.getSession());
        return JsonResult.ok("data",resource);
    }

    private Resource save(String fileName, String downloadUrl, HttpSession session) {
        Resource resource = new Resource();
        User user = (User) session.getAttribute("user");
        resource.setUser(user);
        resource.setName(fileName);
        resource.setUrl(downloadUrl+"/"+fileName);
        resource.setUserId(user.getId());
        resource.setUploadUrl(filePath);
        resource.setType(1);
        resource.setTime(DateUtils.dateToStr(new Date(),"yyyy-mm-dd HH:mm:ss"));
        return resourceService.saveOrUpdate(resource);
    }

    @GetMapping("/download/{name}")
    public void download(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
        File file = new File(filePath+File.separator+name);
        System.out.println(file);
        if(file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + name);

            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        }
    }
}
