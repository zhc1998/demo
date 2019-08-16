package com.jk.dao;

import com.jk.model.Highcharts;
import com.jk.model.User;

import java.util.List;

public interface XxfDao {
    User login(String username);

    List<Highcharts> queryDayCount();
}
