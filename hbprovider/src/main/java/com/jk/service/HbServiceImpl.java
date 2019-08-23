package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.jk.dao.HbDao;
import com.jk.model.Audit;
import com.jk.model.Comments;
import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.model.commodity.CommodityModel;
import com.jk.util.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class HbServiceImpl implements HbService {

    @Autowired
    private HbDao hbDao;

    @Override
    public List<Tree> getTreeAll() {

        return hbDao.getTreeAll();
    }

    @Override
    public ResultPage querymenmbers(ResultPage result) {
        ResultPage resultPage = new ResultPage();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("result", result);

        //查询总条数
        Integer count = hbDao.querymenmberscount(hashMap);
        resultPage.setTotal(count);

        resultPage.setPageNumber(result.getPageNumber());
        resultPage.setPageSize(result.getPageSize());

        hashMap.put("start", (result.getPageNumber()-1)*result.getPageSize());
        hashMap.put("end", result.getPageSize());

        //查询所有数据
        List<CommodityModel> list = hbDao.HashMap(hashMap);
        resultPage.setRows(list);
        return resultPage;


    }

    @Override
    public void saveDialog(Integer userid, String sysNewPWInp) {
        Map map = new HashMap<>();
        map.put("userid",userid);
        map.put("sysNewPWInp",sysNewPWInp);
        hbDao.saveDialog(map);
    }

    @Override
    public void updateAll1(Integer id) {
        hbDao.updateAll1(id);
    }

    @Override
    public void updateAll2(Integer id) {
        hbDao.updateAll2(id);
    }

    @Override
    public Audit login(String name) {
        return hbDao.login(name);
    }

    @Override
    public void updateaudit1(Integer id) {
        hbDao.updateaudit1(id);
    }

    @Override
    public void updateaudit2(Integer id) {
        hbDao.updateaudit2(id);
    }

    @Override
    public ResultPage queryCommodity(ResultPage result) {
        ResultPage resultPage = new ResultPage();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("result", result);

        //查询总条数
        Long count = hbDao.queryCommodityCount(hashMap);
        resultPage.setTotal(Integer.parseInt(count.toString()));

        resultPage.setPageNumber(result.getPageNumber());
        resultPage.setPageSize(result.getPageSize());

        hashMap.put("start", (result.getPageNumber()-1)*result.getPageSize());
        hashMap.put("end", result.getPageSize());

        //查询所有数据
        List<CommodityModel> list = hbDao.queryCommodity(hashMap);
        resultPage.setRows(list);
        return resultPage;
    }

    @Override
    public List<Comments> comments(String id) {
        return hbDao.comments(id);
    }


    //查询审核失败
    @Override
    public ResultPage suditFailure(ResultPage result) {
        ResultPage resultPage = new ResultPage();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("result", result);

        //查询总条数
        Long count = hbDao.suditFailure(hashMap);
        resultPage.setTotal(Integer.parseInt(count.toString()));

        resultPage.setPageNumber(result.getPageNumber());
        resultPage.setPageSize(result.getPageSize());

        hashMap.put("start", (result.getPageNumber()-1)*result.getPageSize());
        hashMap.put("end", result.getPageSize());

        //查询所有数据
        List<CommodityModel> list = hbDao.suditFailureList(hashMap);
        resultPage.setRows(list);
        return resultPage;
    }

    //删除所有审核失败商品
    @Override
    public void delAll(String [] ids) {
        hbDao.delAll(ids);
    }

}
