package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.common.ConstanConf;
import com.jk.service.LmhService;
import com.jk.util.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("lmh")
public class LmhConller {

    @Autowired
    private RedisTemplate redisTemplate;

    @Reference(check = false)
    private LmhService lmhService;

    @RequestMapping("fhsj")
    @ResponseBody
    public Integer fhsj(){

        Integer  gqsj=1;
        String aa="设置过期时间";
        redisTemplate.opsForValue().append(ConstanConf.NAV_CACHE,aa);
        redisTemplate.expire(ConstanConf.NAV_CACHE ,gqsj, TimeUnit.MINUTES);
        return gqsj;
    }


    /**
     * OSS阿里云上传图片
     */
    @RequestMapping("updaloadImg")
    @ResponseBody
    public String uploadImg(MultipartFile imgg)throws IOException {
        if (imgg == null || imgg.getSize() <= 0) {
            throw new IOException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(imgg);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        //System.out.println(split[0]);
        return split[0];
    }

}
