package com.liwj.refreshsession.task;

import com.liwj.refreshsession.pojo.ResultVO;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * @description: 定时任务
 * @author: liwj
 * @create: 2022-09-27 09:33
 **/

@Configuration
@EnableScheduling
public class StaticScheduleTask {
//    @Scheduled(cron = "0/5 * * * * ?")
    //直接指定时间间隔，20分钟一次
    @Scheduled(fixedRate=1200000)
    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        sendPostRequest("https://mobileapi.qinlinkeji.com/api/wxmini/v3/appuser/refresh?sessionId=wxmini:2c9a1958833cfc2901837c8f6b2d4007", null);
//        System.out.println("o = " + o);
    }

    public static Object sendPostRequest(String url, MultiValueMap<String, String> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<Object> response = client.exchange(url, method, requestEntity, Object.class);
        return response.getBody();
    }

}
