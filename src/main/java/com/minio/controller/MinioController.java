package com.minio.controller;

import com.minio.config.MinioComp;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("minio")
public class MinioController {

    @Resource
    private MinioComp comp;

    /**
     * 获取文件上传预签名地址
     *
     * @param objectName 文件名称
     */
    @GetMapping("policyUrl")
    public ResponseEntity<String> policyUrl(@RequestParam("objectName") String objectName) {
        int i = objectName.lastIndexOf(".");
        String suffix = objectName.substring(i);
        String fileName = objectName.substring(0, i) + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String base64FileName = Base64.getEncoder().encodeToString(fileName.getBytes())+suffix;
        String policyUrl = comp.getPolicyUrl(base64FileName, Method.PUT, 1, TimeUnit.HOURS);
        return ResponseEntity.ok(policyUrl);
    }

    /**
     * 获取文件上传预签名地址
     *
     * @param fileName 文件名称
     */
    @GetMapping("saveData")
    public ResponseEntity<String> saveData(@RequestParam("fileName")String fileName) {

        return ResponseEntity.ok(fileName);
    }


}
