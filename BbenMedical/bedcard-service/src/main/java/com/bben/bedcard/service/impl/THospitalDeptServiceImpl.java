package com.bben.bedcard.service.impl;

import com.bben.bedcard.mapper.bedcard.THospitalDeptMapper;
import com.bben.bedcard.model.bedcard.THospitalDept;
import com.bben.bedcard.model.bedcard.THospitalDeptExample;
import com.bben.bedcard.service.THospitalDeptService;
import com.bben.bedcard.vo.THospitalDeptVo;
import com.bben.common.PageInfo;
import com.bben.common.util.A;
import com.bben.common.util.U;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/16 14:43
 */
@Service(value = "THospitalDeptService")
public class THospitalDeptServiceImpl implements THospitalDeptService {

    @Autowired
    private THospitalDeptMapper tHospitalDeptMapper;

    @Override
    public List<THospitalDeptVo> findAll() {
        List<THospitalDept> tHospitalDeptList = tHospitalDeptMapper.selectByExample(null);
        if (A.isEmpty(tHospitalDeptList)){
            return null;
        }
        return THospitalDeptVo.assemblyDataList(tHospitalDeptList);
    }

    @Override
    public THospitalDeptVo selectByPrimaryKey(Integer hospitalDeptId) {
        if (U.isBlank(hospitalDeptId)){
            return null;
        }
        THospitalDept tHospitalDept = tHospitalDeptMapper.selectByPrimaryKey(hospitalDeptId);
        if (U.isBlank(tHospitalDept)){
            return null;
        }
        return THospitalDeptVo.assemblyData(tHospitalDept);
    }

    @Override
    public THospitalDept insertSelective(THospitalDept record) {
        if (U.isBlank(record)){
            return null;
        }
        int count = tHospitalDeptMapper.insertSelective(record);
        if (0 == count){
            return null;
        }
        return record;
    }

    @Override
    public THospitalDept updateByPrimarykey(THospitalDept record) {
        if (U.isBlank(record)){
            return null;
        }
        int count = tHospitalDeptMapper.updateByPrimaryKeySelective(record);
        if (0 == count){
            return null;
        }
        return record;
    }

    @Override
    public int deleteByPrimarykey(Integer hospitalDeptId) {
        if (U.isBlank(hospitalDeptId)){
            return 0;
        }
        return tHospitalDeptMapper.deleteByPrimaryKey(hospitalDeptId);
    }

    @Override
    public PageInfo<THospitalDeptVo> selectByCondition(THospitalDept record, int pageNum, int pageSize) {
        THospitalDeptExample tHospitalDeptExample = new THospitalDeptExample();
        THospitalDeptExample.Criteria criteria = tHospitalDeptExample.createCriteria();
        if (U.isNotBlank(record.getDeptId())){
            criteria.andDeptIdEqualTo(record.getDeptId());
        }
        if (U.isNotBlank(record.getHospitalId())){
            criteria.andHospitalIdEqualTo(record.getHospitalId());
        }
        if (U.isNotBlank(record.getParentDeptId())){
            criteria.andParentDeptIdEqualTo(record.getParentDeptId());
        }
        if (U.isNotBlank(record.getDeptAddress())){
            criteria.andDeptAddressLike(record.getDeptAddress());
        }
        if (U.isNotBlank(record.getDescription())){
            criteria.andDescriptionEqualTo(record.getDescription());
        }
        List<THospitalDept> tHospitalDeptList = tHospitalDeptMapper.selectByExample(tHospitalDeptExample);
        if (A.isEmpty(tHospitalDeptList)){
            return null;
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<THospitalDeptVo> tHospitalDeptVoPageInfo = new PageInfo<>(THospitalDeptVo.assemblyDataList(tHospitalDeptList));
        return tHospitalDeptVoPageInfo;
    }
}
