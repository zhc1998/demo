package com.jk.service;


import com.jk.model.Highcharts;
import com.jk.model.Members;
import com.jk.model.Seckill;
import com.jk.model.User;
import com.jk.util.ResultPage;

import java.util.List;

public interface XxfService {


    User login(String username);

    List<Highcharts> queryDayCount();

    Highcharts queryHighcharts(String time);

    void updateHighcharts(Integer id);

    void addHighcharts(Highcharts highcharts);

    Members frontLogin(String username);

    void addMembers(Members members);

    Members queryMembers(String phone);

    void updateMembers(Members members);

    ResultPage querySeckill(ResultPage result);

    void deleteSeckill(Integer ids);

    Seckill querySeckillById(Integer id);

    void addSeckill2(Seckill seckill);

    void updateSeckill2(Seckill seckill);
}
