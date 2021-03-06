package com.bben.infusion.model.infusion;

import java.io.Serializable;
import java.util.Date;

public class TInfusionEquipType implements Serializable {
    private String infusionEquipType;

    private Integer voltageWarn;

    private Integer lowAlarm;

    private Integer dripWarn;

    private Byte status;

    private String description;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private static final long serialVersionUID = 1L;

    public String getInfusionEquipType() {
        return infusionEquipType;
    }

    public void setInfusionEquipType(String infusionEquipType) {
        this.infusionEquipType = infusionEquipType == null ? null : infusionEquipType.trim();
    }

    public Integer getVoltageWarn() {
        return voltageWarn;
    }

    public void setVoltageWarn(Integer voltageWarn) {
        this.voltageWarn = voltageWarn;
    }

    public Integer getLowAlarm() {
        return lowAlarm;
    }

    public void setLowAlarm(Integer lowAlarm) {
        this.lowAlarm = lowAlarm;
    }

    public Integer getDripWarn() {
        return dripWarn;
    }

    public void setDripWarn(Integer dripWarn) {
        this.dripWarn = dripWarn;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", infusionEquipType=").append(infusionEquipType);
        sb.append(", voltageWarn=").append(voltageWarn);
        sb.append(", lowAlarm=").append(lowAlarm);
        sb.append(", dripWarn=").append(dripWarn);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}