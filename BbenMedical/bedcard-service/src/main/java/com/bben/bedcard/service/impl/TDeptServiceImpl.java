package com.bben.bedcard.service.impl;

import com.bben.bedcard.mapper.bedcard.TDeptMapper;
import com.bben.bedcard.model.bedcard.TDept;
import com.bben.bedcard.model.bedcard.TDeptExample;
import com.bben.bedcard.model.bedcard.TDoctor;
import com.bben.bedcard.service.TDeptService;
import com.bben.bedcard.vo.TDeptVo;
import com.bben.bedcard.vo.TDoctorVo;
import com.bben.common.PageInfo;
import com.bben.common.util.A;
import com.bben.common.util.U;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/16 13:49
 */
@Service(value = "TDeptService")
public class TDeptServiceImpl implements TDeptService {

    @Autowired
    private TDeptMapper tDeptMapper;

    @Override
    public List<TDeptVo> findAll() {
        List<TDept> tDeptList = tDeptMapper.selectByExample(null);
        if (A.isEmpty(tDeptList)){
            return null;
        }
        return TDeptVo.assemblyDataList(tDeptList);
    }

    @Override
    public TDeptVo selectByPrimaryKey(Integer deptId) {
        if (U.isBlank(deptId)){
            return null;
        }
        TDept tDept = tDeptMapper.selectByPrimaryKey(deptId);
        if (U.isBlank(tDept)){
            return null;
        }
        return TDeptVo.assemblyData(tDept);
    }

    @Override
    public TDept insertSelective(TDept record) {
        if (U.isBlank(record)){
            return null;
        }
        int count = tDeptMapper.insertSelective(record);
        if (0 == count){
            return null;
        }
        return record;
    }

    @Override
    public TDept updateByPrimarykey(TDept record) {
        if (U.isBlank(record)){
            return null;
        }
        int count = tDeptMapper.updateByPrimaryKeySelective(record);
        if (0 == count){
            return null;
        }
        return record;
    }

    @Override
    public int deleteByPrimarykey(Integer deptId) {
        if (U.isBlank(deptId)){
            return 0;
        }
        return tDeptMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public PageInfo<TDeptVo> selectByCondition(TDept record, int pageNum, int pageSize) {
        TDeptExample tDeptExample = new TDeptExample();
        TDeptExample.Criteria criteria = tDeptExample.createCriteria();
        if (U.isNotBlank(record.getName())){
            criteria.andNameLike(record.getName());
        }
        if (U.isNotBlank(record.getIntro())){
            criteria.andIntroLike(record.getIntro());
        }
        if (U.isNotBlank(record.getDescription())){
            criteria.andDescriptionEqualTo(record.getDescription());
        }
        List<TDept> tDeptList = tDeptMapper.selectByExample(tDeptExample);
        if (A.isEmpty(tDeptList)){
            return null;
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<TDeptVo> tDeptVoPageInfo = new PageInfo<>(TDeptVo.assemblyDataList(tDeptList));
        return tDeptVoPageInfo;
    }
}
