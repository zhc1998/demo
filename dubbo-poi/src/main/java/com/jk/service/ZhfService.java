package com.jk.service;

import com.jk.model.Familyhead;
import com.jk.util.ParameUtil;

import java.util.HashMap;

public interface ZhfService {
    Familyhead loginf(Familyhead familyhead);


    HashMap<String, Object> queryorderone(ParameUtil parame);
}
