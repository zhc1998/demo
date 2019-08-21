package com.jk.service;

import com.jk.model.Audit;
import com.jk.model.Tree;
import com.jk.model.User;
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

    ResultPage suditFailure(ResultPage result);

    void delAll(Integer [] ids);
}
