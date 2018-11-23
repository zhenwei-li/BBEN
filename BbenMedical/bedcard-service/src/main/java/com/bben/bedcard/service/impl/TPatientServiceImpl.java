package com.bben.bedcard.service.impl;

import com.bben.bedcard.mapper.bedcard.TPatientMapper;
import com.bben.bedcard.model.bedcard.TPatient;
import com.bben.bedcard.model.bedcard.TPatientExample;
import com.bben.bedcard.service.TPatientService;
import com.bben.bedcard.vo.TPatientVo;
import com.bben.common.PageInfo;
import com.bben.common.util.A;
import com.bben.common.util.U;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/16 17:30
 */
@Service(value = "TPatientService")
public class TPatientServiceImpl implements TPatientService {


    @Autowired
    private TPatientMapper tPatientMapper;

    @Override
    public List<TPatientVo> findAll() {
        List<TPatient> tPatientList = tPatientMapper.selectByExample(null);
        if (A.isEmpty(tPatientList)){
            return null;
        }
        return TPatientVo.assemblyDataList(tPatientList);
    }

    @Override
    public TPatientVo selectByPrimaryKey(Integer patientId) {
        if (U.isBlank(patientId)){
            return null;
        }
        TPatient tPatient = tPatientMapper.selectByPrimaryKey(patientId);
        if (U.isBlank(tPatient)){
            return null;
        }
        return TPatientVo.assemblyData(tPatient);
    }

    @Override
    public TPatient insertSelective(TPatient record) {
        if (U.isBlank(record)){
            return null;
        }
        int count = tPatientMapper.insertSelective(record);
        if (0 == count){
            return null;
        }
        return record;
    }

    @Override
    public TPatient updateByPrimarykey(TPatient record) {
        if (U.isBlank(record)){
            return null;
        }
        int count = tPatientMapper.updateByPrimaryKeySelective(record);
        if (0 == count){
            return null;
        }
        return record;
    }

    @Override
    public int deleteByPrimarykey(Integer patientId) {
        if (U.isBlank(patientId)){
            return 0;
        }
        return tPatientMapper.deleteByPrimaryKey(patientId);
    }

    @Override
    public PageInfo<TPatientVo> selectByCondition(TPatient record, int pageNum, int pageSize) {
        TPatientExample tPatientExample = new TPatientExample();
        TPatientExample.Criteria criteria = tPatientExample.createCriteria();
        if (U.isNotBlank(record.getCode())){
            criteria.andCodeEqualTo(record.getCode());
        }
        if (U.isNotBlank(record.getName())){
            criteria.andNameEqualTo(record.getName());
        }
        if (U.isNotBlank(record.getHospitalId())){
            criteria.andHospitalIdEqualTo(record.getHospitalId());
        }
        if (U.isNotBlank(record.getDistrictId())){
            criteria.andDistrictIdEqualTo(record.getDistrictId());
        }
        if (U.isNotBlank(record.getDeptId())){
            criteria.andDeptIdEqualTo(record.getDeptId());
        }
        if (U.isNotBlank(record.getWardId())){
            criteria.andWardIdEqualTo(record.getWardId());
        }
        if (U.isNotBlank(record.getSickroomNo())){
            criteria.andSickroomNoEqualTo(record.getSickroomNo());
        }
        if (U.isNotBlank(record.getCureNo())){
            criteria.andCureNoEqualTo(record.getCureNo());
        }
        if (U.isNotBlank(record.getBedNo())){
            criteria.andBedNoEqualTo(record.getBedNo());
        }
        if (U.isNotBlank(record.getNurseLevel())){
            criteria.andNurseLevelEqualTo(record.getNurseLevel());
        }
        if (U.isNotBlank(record.getMobile())){
            criteria.andMobileEqualTo(record.getMobile());
        }
        if (U.isNotBlank(record.getIdNumber())){
            criteria.andIdNumberLike(record.getIdNumber());
        }
        if (U.isNotBlank(record.getNurseLevel())){
            criteria.andNurseLevelEqualTo(record.getNurseLevel());
        }
        List<TPatient> tPatientList = tPatientMapper.selectByExample(tPatientExample);
        if (A.isEmpty(tPatientList)){
            return null;
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<TPatientVo> tPatientVoPageInfo = new PageInfo<>(TPatientVo.assemblyDataList(tPatientList));
        return tPatientVoPageInfo;
    }
}
