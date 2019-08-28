package com.jk.controller;

import com.jk.model.Members;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("zhc")
public class ZhcController {

    @RequestMapping("getLoginName")
    @ResponseBody
    public String getLoginName(HttpSession session) {
        Members members = (Members) session.getAttribute("members");
        String userName="";
        if(members!=null){
            userName=members.getUsername();
        }
        return userName;
    }
}
