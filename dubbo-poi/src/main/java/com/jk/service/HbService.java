package com.jk.service;

import com.jk.model.Audit;
import com.jk.model.Comments;
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

    List<Comments> comments(Integer id);

    ResultPage suditFailure(ResultPage result);

    void delAll(Integer [] ids);
}
