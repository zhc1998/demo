/**
 * Copyright (C), 2019, XXX有限公司
 * FileName: ClpServiceImpl
 * Author:   clp
 * Date:     2019/8/14 20:38
 * Description: clp-授课教师端实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.YhqMapper;
import com.jk.model.QueryYhq;
import com.jk.model.Yhq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈clp-授课教师端实现〉
 *
 * @author clp
 * @create 2019/8/14
 * @since 1.0.0
 */
@Service
public class ClpServiceImpl implements ClpService{




    @Autowired
    private YhqMapper yhqMapper;




    @Override
    public Map queryYhq(QueryYhq yhq) {
        int sta=(yhq.getPageNumber()-1)*yhq.getPageSize();
        List<Yhq> list=yhqMapper.queryYhq(sta,yhq.getPageSize());
        long count=yhqMapper.queryCount();
        Map map=new HashMap();
        map.put("rows",list);
        map.put("total",count);
        return map;
    }



    @Override
    public Yhq toUpdClpYhqPage(Integer id) {
        return yhqMapper.toUpdClpYhqPage(id);
    }

    @Override
    public void updateYhq(Yhq yhq) {
        yhqMapper.updateByPrimaryKeySelective(yhq);
    }

    @Override
    @Async
    public void addYhq(List<Yhq> list) {
         yhqMapper.addYhq(list);
    }

    @Override
    public void deleteYhqByName(String names) {
         yhqMapper.deleteYhqByName(names);
    }

    @Override
    public List<Yhq> queryClpYhq(Integer id) {
        return yhqMapper.queryClpYhq(id);
    }

    @Override
    public List<Yhq> queryClpYhq2(Integer mid) {
        return yhqMapper.queryClpYhq2(mid);
    }

    @Override
    public void updateYhqUse(Integer id,Integer mid) {
        yhqMapper.updateYhqUse(id,mid);
    }

    @Override
    public List<Yhq> queryClpYhqs() {
        return yhqMapper.queryClpYhqs();
    }


}
