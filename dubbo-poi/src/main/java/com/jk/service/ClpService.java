package com.jk.service;

import com.jk.model.QueryYhq;
import com.jk.model.Yhq;

import java.util.List;
import java.util.Map;

public interface ClpService {

    Map queryYhq(QueryYhq yhq);


    Yhq toUpdClpYhqPage(Integer id);

    void updateYhq(Yhq yhq);

    void addYhq(List<Yhq> list);

    void deleteYhqByName(String names);

    List<Yhq> queryClpYhq();

    List<Yhq> queryClpYhq2(int i);

    void updateYhqUse(Integer id);

    List<Yhq> queryClpYhqByName(String yhqname);
}
