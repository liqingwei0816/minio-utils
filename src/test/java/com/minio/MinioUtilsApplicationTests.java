package com.minio;

import com.minio.config.MinioComp;
import com.minio.config.MinioConfiguration;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class MinioUtilsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private MinioConfiguration configuration;

    @Test
    void Config() {
        System.out.println(configuration.getAccessKey());
    }
    @Resource
    private MinioComp comp;

    /**
     * 获取预上传地址后反给前端，由前端直连minio服务上传，可使用Nginx代理minio地址
     */
    @Test
    void compGetPolicyUrl() {
        String url = comp.getPolicyUrl("data/kabi2.zip", Method.PUT,1, TimeUnit.HOURS);
        System.out.println(url);
    }
    @Test
    void compGetUrl() {
        String url = comp.getUrl("data/kabi.zip", 1, TimeUnit.HOURS);
        System.out.println(url);
    }

}
