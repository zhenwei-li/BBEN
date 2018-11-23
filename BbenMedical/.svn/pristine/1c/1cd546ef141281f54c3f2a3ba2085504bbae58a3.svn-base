package com.bben.bedcard.controller;

import com.bben.bedcard.dto.TDeptDto;
import com.bben.bedcard.model.bedcard.TDept;
import com.bben.bedcard.service.TDeptService;
import com.bben.bedcard.vo.TDeptVo;
import com.bben.common.BaseController;
import com.bben.common.PageInfo;
import com.bben.common.entity.vo.Result;
import com.bben.common.util.U;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Lizhenwei
 * @Date: 2018/11/16 14:17
 */
@Api(tags = "科室接口")
@RestController
@RequestMapping("/dept")
public class TDeptController extends BaseController {

    @Autowired
    private TDeptService tDeptService;

    public Result addTDeptError(TDeptDto tDeptDto){
        return Result.fail("添加科室失败");
    }

    public Result updateTDeptError(TDept tDept){
        return Result.fail("更新科室失败");
    }

    @GetMapping("/selectByDeptId")
    @ApiOperation("根据Id查询科室信息 -> 李振伟")
    public Result<TDeptVo> selectByDeptId(@ApiParam("科室Id") @RequestParam Integer deptId){
        if (U.isBlank(deptId)){
            return Result.fail("参数不能为空");
        }
        TDeptVo tDeptVo = tDeptService.selectByPrimaryKey(deptId);
        return  Result.success("查询数据成功",tDeptVo);
    }

    @PostMapping("/addTDept")
    @ApiOperation("添加科室信息 -> 李振伟")
    @HystrixCommand(fallbackMethod = "addTDeptError")
    public Result addTDept(TDeptDto tDeptDto){
        if (U.isBlank(tDeptDto)){
            return Result.fail("对象不能为空");
        }
        TDept ok = tDeptService.insertSelective(TDeptDto.assemblyData(tDeptDto));
        if (U.isBlank(ok)){
            return Result.fail("添加数据失败");
        }
        log.info("TRoleId : "+ok.getId());
        return Result.success("添加数据成功");

    }

    @PostMapping("/updateTDept")
    @ApiOperation("更新科室信息 -> 李振伟")
    @HystrixCommand(fallbackMethod = "updateTDeptError")
    public Result updateTDept(TDept tDept){
        if (U.isBlank(tDept)){
            return Result.fail("对象不能为空");
        }
        TDept ok = tDeptService.updateByPrimarykey(tDept);
        if (U.isBlank(ok)){
            return Result.fail("更新数据失败");
        }
        return Result.success("更新数据成功");
    }

    @DeleteMapping("/deleteByDeptId")
    @ApiOperation("删除科室信息 -> 李振伟")
    public Result deleteByDeptId(@ApiParam("科室id") @RequestParam Integer deptId){
        if (U.isBlank(deptId)){
            return Result.fail("参数不能为空");
        }
        int ok = tDeptService.deleteByPrimarykey(deptId);
        if (0 == ok){
            return Result.fail("删除数据失败");
        }
        return Result.success("删除数据成功");
    }

    @PostMapping("/getTDeptVoByCondition")
    @ApiOperation("根据条件查询科室信息 -> 李振伟")
    public  Result<PageInfo<TDeptVo>> getTDeptVoByCondition(TDeptDto tDeptDto,
                                                            @RequestParam(name = "pageNum",required = false,defaultValue = "1")int pageNum,
                                                            @RequestParam(name = "pageSize",required = false,defaultValue = "10")int pageSize){
        PageInfo<TDeptVo> tDeptVoPageInfo = tDeptService.selectByCondition(TDeptDto.assemblyData(tDeptDto),pageNum,pageSize);
        return Result.success("查询科室信息成功",tDeptVoPageInfo);
    }
}
