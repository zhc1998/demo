package com.jk.service;


import com.jk.model.Highcharts;
import com.jk.model.User;

import java.util.List;

public interface XxfService {


    User login(String username);

    List<Highcharts> queryDayCount();
}
