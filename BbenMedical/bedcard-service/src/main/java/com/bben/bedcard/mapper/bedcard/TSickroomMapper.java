package com.bben.bedcard.mapper.bedcard;

import com.bben.bedcard.model.bedcard.TSickroom;
import com.bben.bedcard.model.bedcard.TSickroomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSickroomMapper {
    long countByExample(TSickroomExample example);

    int deleteByExample(TSickroomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSickroom record);

    int insertSelective(TSickroom record);

    List<TSickroom> selectByExample(TSickroomExample example);

    TSickroom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSickroom record, @Param("example") TSickroomExample example);

    int updateByExample(@Param("record") TSickroom record, @Param("example") TSickroomExample example);

    int updateByPrimaryKeySelective(TSickroom record);

    int updateByPrimaryKey(TSickroom record);
}