package com.jk.service;

import com.jk.model.commodity.CommodityTypeModel;
import com.jk.util.ResultPage;

import java.util.List;

public interface ZcService {

    ResultPage queryCommodity(ResultPage result);

    List<CommodityTypeModel> queryCommodityType();
}
