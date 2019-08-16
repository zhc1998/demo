package com.jk.service;

import com.jk.model.Tree;
import com.jk.util.ResultPage;

import java.util.List;

public interface HbService {
    List<Tree> getTreeAll();

    ResultPage querymenmbers(ResultPage result);
}
