package com.bben.bedcard.service.impl;

import com.bben.bedcard.mapper.bedcard.TDeptMapper;
import com.bben.bedcard.mapper.bedcard.THospitalDeptMapper;
import com.bben.bedcard.mapper.bedcard.TNurseMapper;
import com.bben.bedcard.model.bedcard.TDept;
import com.bben.bedcard.model.bedcard.THospitalDept;
import com.bben.bedcard.model.bedcard.TNurse;
import com.bben.bedcard.service.TNurseService;
import com.bben.bedcard.vo.TDeptVo;
import com.bben.bedcard.vo.TNurseVo;
import com.bben.common.util.A;
import com.bben.common.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/12 15:51
 */
@Service(value = "TNurseService")
public class TNurseServiceImpl implements TNurseService {

    @Autowired
    private TNurseMapper tNurseMapper;

    @Autowired
    private THospitalDeptMapper tHospitalDeptMapper;

    @Autowired
    private TDeptMapper tDeptMapper;

    @Override
    public TNurseVo selectByPrimaryKey(Integer nurseId) {
        if (U.isBlank(nurseId)){
            return null;
        }
        TNurse tNurse = tNurseMapper.selectByPrimaryKey(nurseId);
        return assemblyData(tNurse);
    }

    private TNurseVo assemblyData(TNurse tNurse){
        TNurseVo tNurseVo;
        if (U.isBlank(tNurse)){
            return null;
        }
        tNurseVo = TNurseVo.assemblyData(tNurse);
        if (U.isNotBlank(tNurse.getHospitalDeptId())){
            THospitalDept tHospitalDept = tHospitalDeptMapper.selectByPrimaryKey(tNurse.getHospitalDeptId());
            if (U.isNotBlank(tHospitalDept) || U.isNotBlank(tHospitalDept.getDeptId())){
                TDept tDept = tDeptMapper.selectByPrimaryKey(tHospitalDept.getDeptId());
                if (U.isNotBlank(tDept)){
                    tNurseVo.setTDeptVo(TDeptVo.assemblyData(tDept));
                }
            }
        }
        return tNurseVo;
    }

    private List<TNurseVo> assemblyDataList(List<TNurse> tNurseList){
        List<TNurseVo> tNurseVoList = new ArrayList<>();
        if (A.isEmpty(tNurseVoList)){
            return null;
        }
        for (TNurse tNurse : tNurseList) {
            TNurseVo tNurseVo = assemblyData(tNurse);
            tNurseVoList.add(tNurseVo);
        }
        return tNurseVoList;
    }


}
