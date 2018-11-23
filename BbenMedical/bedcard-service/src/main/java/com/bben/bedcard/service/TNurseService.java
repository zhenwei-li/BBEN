package com.bben.bedcard.service;

import com.bben.bedcard.vo.TNurseVo;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/12 15:42
 */
public interface TNurseService {

    /**
     * 根据主键查询护士信息
     * @param nurseId
     * @return
     */
    TNurseVo selectByPrimaryKey(Integer nurseId);

}
