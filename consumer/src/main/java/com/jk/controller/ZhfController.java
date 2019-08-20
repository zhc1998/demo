package com.jk.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jk.model.Express;
import com.jk.model.Familyhead;
import com.jk.model.Orderone;
import com.jk.service.ZhfService;
import com.jk.util.HttpClientUtil;
import com.jk.util.ParameUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //查询订单详情
    @RequestMapping("showorderinfo")
    public String showorderinfo(Integer id, Model model){
        Orderone orderone= zhfService.queryorderbyid(id);
        model.addAttribute("orderone",orderone);
        return "showorderinfo";
    }
//查询订单的商品列表
    @RequestMapping("queryordertable")
    @ResponseBody
    public HashMap<String, Object> queryordertable(Integer oid) {

        return zhfService.queryordertable(oid);
    }

    //查询(自己)登录人的订单列表
    @RequestMapping("queryorderbyuid")
    @ResponseBody
    public HashMap<String, Object> queryorderbyuid(@RequestBody ParameUtil parame) {
       Integer userid=1;
        return zhfService.queryorderbyuid(userid,parame);
    }
    //快递单号查询
    @RequestMapping("querytnumber")
    @ResponseBody
    public HashMap<String,Object>querytnumber(@RequestBody ParameUtil parame) throws Exception {
         String url= "http://www.kuaidi100.com/query";
        HashMap<String,Object>params=new HashMap<>();
        System.err.println(parame.getType());
        System.err.println(parame.getPostid());
        params.put("type",parame.getType());
        params.put("postid",parame.getPostid());
        String string = HttpClientUtil.get(url, params);
        System.err.println(string);
        JSONObject jsonObject= JSONObject.parseObject(string);
        String datastr = jsonObject.getString("data");
        JSONArray  arr = JSONArray.parseArray(datastr);
        List<Express> list = (List<Express>) JSONObject.parseArray(arr.toJSONString(), Express.class);
        HashMap<String,Object>map=new HashMap<>();
        map.put("rows",list);
        return map;
    }
}
