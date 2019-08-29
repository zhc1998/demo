package com.jk.controller;

import com.jk.model.Members;
import com.jk.model.commodity.CommodityModel;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("zhc")
public class ZhcController {

    @Autowired
    private SolrClient client;

    @RequestMapping("getLoginName")
    @ResponseBody
    public String getLoginName(HttpSession session) {
        Members members = (Members) session.getAttribute("members");
        String userName="";
        if(members!=null){
            userName=members.getNickname();
        }
        return userName;
    }
}
