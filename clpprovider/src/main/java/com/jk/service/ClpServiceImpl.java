/**
 * Copyright (C), 2019, XXX有限公司
 * FileName: ClpServiceImpl
 * Author:   clp
 * Date:     2019/8/14 20:38
 * Description: clp-授课教师端实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.ClpTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈一句话功能简述〉<br> 
 * 〈clp-授课教师端实现〉
 *
 * @author clp
 * @create 2019/8/14
 * @since 1.0.0
 */
@Service
public class ClpServiceImpl implements ClpService{

    @Autowired
    private ClpTeacherMapper clpTeacherMapper;

}
