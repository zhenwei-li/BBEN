package com.bben.bedcard.service;

import com.bben.bedcard.model.bedcard.TDoctor;
import com.bben.bedcard.vo.TDoctorVo;
import com.bben.common.PageInfo;

import java.util.List;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/12 15:02
 */
public interface TDoctorService {

    /**
     * 查询所有医生信息
     * @return
     */
    List<TDoctorVo> findAllTDoctor();

    /**
     * 根据主键查询医生信息
     * @param doctorId
     * @return
     */
    TDoctorVo selectByPrimaryKey(Integer doctorId);

    /**
     * 添加医生信息
     * @param tDoctor
     * @return
     */
    TDoctor insertSelective(TDoctor tDoctor);

    /**
     * 更新医生信息
     * @param tDoctor
     * @return
     */
    TDoctor updateByPrimarykey(TDoctor tDoctor);

    /**
     * 删除医生信息
     * @param doctorId
     * @return
     */
    int deleteByPrimarykey(Integer doctorId);

    /**
     * 根据条件查询分页信息
     * @param record
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<TDoctorVo> selectByCondition(TDoctor record,int pageNum,int pageSize);

}
