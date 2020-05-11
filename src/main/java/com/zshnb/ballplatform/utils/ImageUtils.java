package com.zshnb.ballplatform.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * CreateDate：2020/5/6 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
@Component
public class ImageUtils {

    @Value("${spring.file.upload-dir}")
    private String uploadPath;

    public String uploadImg(MultipartFile multipartFile) {
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        try (FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uploadPath + File.separator + fileName))) {
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}