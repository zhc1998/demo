package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.TestMapper;
import com.jk.model.TestModel;
import com.jk.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testmapper;

    @Override
    public List<TestModel> query() {

        return testmapper.query();
    }
}
