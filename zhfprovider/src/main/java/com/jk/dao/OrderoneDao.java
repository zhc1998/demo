package com.jk.dao;

import com.jk.model.Orderone;
import com.jk.util.ParameUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderoneDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Orderone record);

    int insertSelective(Orderone record);

    Orderone selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orderone record);

    int updateByPrimaryKey(Orderone record);

    long QueryorOrderonecount(@Param("pa") ParameUtil parame);

    List<Orderone> queryOreryone(@Param("st") int statr,@Param("ro") Integer pageNumber,@Param("pa") ParameUtil parame);
   @Select("select t.commodityName commodityname,o.artNo,t.commodityPrice commodityprice,o.amount,o.amountpayable from orderone o,t_item t where o.artno=t.artNo and o.id=#{oid}")
    Orderone queryordertable(@Param("oid") Integer oid);
@Select("select count(*) from orderone where userid=#{userid}")
    long QueryorOrderbyuidcount(@Param("userid") Integer userid);
@Select(" select *  from orderone where userid=#{userid} limit #{st},#{ro}")
    List<Orderone> queryorderbyuid(@Param("st")int statr,@Param("ro") Integer pageSize,@Param("userid") Integer userid);
}