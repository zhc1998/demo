package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.LmhMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class LmhServiceImpl implements LmhService {

    @Autowired
    private LmhMapper lmhMapper;

}
