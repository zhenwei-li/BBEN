package com.bben.bedcard.service.impl;

import com.bben.bedcard.mapper.bedcard.TDeptMapper;
import com.bben.bedcard.mapper.bedcard.TDoctorMapper;
import com.bben.bedcard.mapper.bedcard.THospitalDeptMapper;
import com.bben.bedcard.model.bedcard.TDept;
import com.bben.bedcard.model.bedcard.TDoctor;
import com.bben.bedcard.model.bedcard.TDoctorExample;
import com.bben.bedcard.model.bedcard.THospitalDept;
import com.bben.bedcard.service.TDoctorService;
import com.bben.bedcard.vo.TDeptVo;
import com.bben.bedcard.vo.TDoctorVo;
import com.bben.common.PageInfo;
import com.bben.common.util.A;
import com.bben.common.util.U;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/12 15:02
 */
@Service(value = "TDoctorService")
public class TDoctorServiceImpl implements TDoctorService {

    @Autowired
    private TDoctorMapper tDoctorMapper;

    @Autowired
    private THospitalDeptMapper tHospitalDeptMapper;

    @Autowired
    private TDeptMapper tDeptMapper;

    @Override
    public List<TDoctorVo> findAllTDoctor() {
        List<TDoctor> tDoctorList = tDoctorMapper.selectByExample(null);
        if (A.isEmpty(tDoctorList)){
            return null;
        }
        List<TDoctorVo> tDoctorVoList = assemblyDataList(tDoctorList);
        return tDoctorVoList;
    }

    @Override
    public TDoctorVo selectByPrimaryKey(Integer doctorId) {
        if (U.isBlank(doctorId)){
            return null;
        }
        TDoctor tDoctor = tDoctorMapper.selectByPrimaryKey(doctorId);
        return assemblyData(tDoctor);
    }

    @Override
    public TDoctor insertSelective(TDoctor record) {
        if (U.isBlank(record)){
            return null;
        }
        int count = tDoctorMapper.insertSelective(record);
        if (0 == count){
            return null;
        }
        return record;
    }

    @Override
    public TDoctor updateByPrimarykey(TDoctor record) {
        if (U.isBlank(record)){
            return null;
        }
        int count = tDoctorMapper.updateByPrimaryKeySelective(record);
        if (0 == count){
            return null;
        }
        return record;
    }

    @Override
    public int deleteByPrimarykey(Integer doctorId) {
        if (U.isBlank(doctorId)){
            return 0;
        }
        return tDoctorMapper.deleteByPrimaryKey(doctorId);
    }

    @Override
    public PageInfo<TDoctorVo> selectByCondition(TDoctor record, int pageNum, int pageSize) {
        TDoctorExample tDoctorExample = new TDoctorExample();
        TDoctorExample.Criteria criteriaDoctor = tDoctorExample.createCriteria();
        if (U.isNotBlank(record.getCode())){
            criteriaDoctor.andCodeEqualTo(record.getCode());
        }
        if (U.isNotBlank(record.getHospitalId())){
            criteriaDoctor.andHospitalIdEqualTo(record.getHospitalId());
        }
        if (U.isNotBlank(record.getHospitalDeptId())){
            criteriaDoctor.andHospitalDeptIdEqualTo(record.getHospitalDeptId());
        }
        if (U.isNotBlank(record.getUserId())){
            criteriaDoctor.andUserIdEqualTo(record.getUserId());
        }
        if (U.isNotBlank(record.getDoctorName())){
            criteriaDoctor.andDoctorNameLike(record.getDoctorName());
        }
        if (U.isNotBlank(record.getSex())){
            criteriaDoctor.andSexEqualTo(record.getSex());
        }
        if (U.isNotBlank(record.getMarriage())){
            criteriaDoctor.andMarriageEqualTo(record.getMarriage());
        }
        if (U.isNotBlank(record.getMobile())){
            criteriaDoctor.andMobileEqualTo(record.getMobile());
        }
        if (U.isNotBlank(record.getIsExpert())){
            criteriaDoctor.andIsExpertEqualTo(record.getIsExpert());
        }
        if (U.isNotBlank(record.getIdNumber())){
            criteriaDoctor.andIdNumberEqualTo(record.getIdNumber());
        }
        if (U.isNotBlank(record.getCheckState())){
            criteriaDoctor.andCheckStateEqualTo(record.getCheckState());
        }
        List<TDoctor> tDoctorList = tDoctorMapper.selectByExample(tDoctorExample);
        if (A.isEmpty(tDoctorList)){
            return null;
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<TDoctorVo> tDoctorVoPageInfo = new PageInfo<>(TDoctorVo.assemblyDataList(tDoctorList));
        return tDoctorVoPageInfo;
    }

    private TDoctorVo assemblyData(TDoctor tDoctor){
        TDoctorVo tDoctorVo = new TDoctorVo();
        if (U.isBlank(tDoctor)){
            return tDoctorVo;
        }
        tDoctorVo = TDoctorVo.assemblyData(tDoctor);
        if (U.isNotBlank(tDoctor.getHospitalDeptId())){
            THospitalDept tHospitalDept = tHospitalDeptMapper.selectByPrimaryKey(tDoctor.getHospitalDeptId());
            if (U.isNotBlank(tHospitalDept) || U.isNotBlank(tHospitalDept.getDeptId())){
                TDept tDept = tDeptMapper.selectByPrimaryKey(tHospitalDept.getDeptId());
                if (U.isNotBlank(tDept)){
                    tDoctorVo.setTDeptVo(TDeptVo.assemblyData(tDept));
                }
            }
        }
        return tDoctorVo;
    }

    private List<TDoctorVo> assemblyDataList(List<TDoctor> tDoctorList){
        List<TDoctorVo> tDoctorVoList = new ArrayList<>();
        if (A.isEmpty(tDoctorList)){
            return tDoctorVoList;
        }
        for (TDoctor tDoctor : tDoctorList) {
            TDoctorVo tDoctorVo = assemblyData(tDoctor);
            tDoctorVoList.add(tDoctorVo);
        }
        return tDoctorVoList;
    }

}
