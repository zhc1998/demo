/**
 * Copyright (C), 2019-2019, 金科
 * FileName: HbDao
 * Author:   黄斌
 * Date:     2019/8/15 15:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.dao;

import com.jk.model.Tree;
import com.jk.model.commodity.CommodityModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 斌
 * @create 2019/8/15
 * @since 1.0.0
 */
public interface HbDao {


    List<Tree> getTreeAll();

    Integer querymenmberscount(HashMap<String, Object> hashMap);

    List<CommodityModel> HashMap(HashMap<String, Object> hashMap);

    void saveDialog(Map map);
}
