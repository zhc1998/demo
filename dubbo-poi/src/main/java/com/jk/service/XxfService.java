package com.jk.service;


import com.jk.model.Highcharts;
import com.jk.model.Members;
import com.jk.model.User;

import java.util.List;

public interface XxfService {


    User login(String username);

    List<Highcharts> queryDayCount();

    Highcharts queryHighcharts(String time);

    void updateHighcharts(Integer id);

    void addHighcharts(Highcharts highcharts);

    Members frontLogin(String username);

    Members yz(String username);
}
