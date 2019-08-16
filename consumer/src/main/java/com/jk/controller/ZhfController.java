package com.jk.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Familyhead;
import com.jk.service.ZhfService;
import com.jk.util.ParameUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("zhf")
public class ZhfController {
    @Reference
    private ZhfService zhfService;

    @RequestMapping("familylogin")
    public String familylogin(){
        return "familylogin";
    }
    //登录家长
    @RequestMapping("loginfuser")
    @ResponseBody
    public Integer loginfuser(Familyhead familyhead, HttpServletRequest request){
        Familyhead familyhead1 = zhfService.loginf(familyhead);
        if (familyhead1 != null) {
            if (familyhead.getUserpwd().equals(familyhead1.getUserpwd())) {
                HttpSession session = request.getSession();
                session.setAttribute("familyhead", familyhead);
                return 3;
            } else {
                //密码错误
                return 2;
            }
        }
// 1用户不存在
        return 1;
    }



    //查询普通订单
    @RequestMapping("queryorderone")
    @ResponseBody
    public HashMap<String, Object> queryorderone(@RequestBody ParameUtil parame){
        return zhfService.queryorderone(parame);
    }
}
