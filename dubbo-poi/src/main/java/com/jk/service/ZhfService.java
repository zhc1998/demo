package com.jk.service;

import com.jk.model.Familyhead;
import com.jk.model.Orderone;
import com.jk.util.ParameUtil;

import java.util.HashMap;

public interface ZhfService {
    Familyhead loginf(Familyhead familyhead);

    HashMap<String, Object> queryorderone(ParameUtil parame);

    Orderone queryorderbyid(Integer id);

    HashMap<String, Object> queryordertable(Integer oid);

    HashMap<String, Object> queryorderbyuid(Integer userid, ParameUtil parame);

    void addorder(Orderone orderone);
}
