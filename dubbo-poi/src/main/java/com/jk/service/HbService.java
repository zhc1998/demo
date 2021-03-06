package com.jk.service;

import com.jk.model.*;
import com.jk.util.ResultPage;

import java.util.List;

public interface HbService {
    List<Tree> getTreeAll();

    ResultPage querymenmbers(ResultPage result);

    void saveDialog(Integer userid, String sysNewPWInp);

    void updateAll1(Integer id);

    void updateAll2(Integer id);

    Audit login(String name);

    void updateaudit1(Integer id);

    void updateaudit2(Integer id);

    ResultPage queryCommodity(ResultPage result);

    List<Comments> comments(Integer id);

    ResultPage suditFailure(ResultPage result);

    void delAll(Integer [] ids);

    void addevaluation(String text, Integer ids, String username);


    List<Comments> qurtycom();

    List<Comments> reply(Integer obj);
}
