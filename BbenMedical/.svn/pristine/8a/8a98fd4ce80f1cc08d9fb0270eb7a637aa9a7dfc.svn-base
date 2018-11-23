package com.bben.bedcard.service;

import com.bben.bedcard.model.bedcard.THospitalDept;
import com.bben.bedcard.vo.THospitalDeptVo;
import com.bben.common.PageInfo;

import java.util.List;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/16 14:36
 */
public interface THospitalDeptService {

    /**
     * 查询所有信息
     * @return
     */
    List<THospitalDeptVo> findAll();

    /**
     * 根据主键查询信息
     * @param hospitalDeptId
     * @return
     */
    THospitalDeptVo selectByPrimaryKey(Integer hospitalDeptId);

    /**
     * 添加信息
     * @param record
     * @return
     */
    THospitalDept insertSelective(THospitalDept record);

    /**
     * 更新信息
     * @param record
     * @return
     */
    THospitalDept updateByPrimarykey(THospitalDept record);

    /**
     * 删除信息
     * @param hospitalDeptId
     * @return
     */
    int deleteByPrimarykey(Integer hospitalDeptId);

    /**
     * 根据条件查询分页信息
     * @param record
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<THospitalDeptVo> selectByCondition(THospitalDept record, int pageNum, int pageSize);
}
