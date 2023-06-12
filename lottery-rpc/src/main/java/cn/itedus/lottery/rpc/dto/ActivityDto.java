package cn.itedus.lottery.rpc.dto;

import java.util.Date;

/**
 * question:啥是DTO？为啥要用DTO
 * DTO是数据传输对象
 * 表现层与应用层之间是通过数据传输对象（DTO）进行交互，目的只是为了对领域对象进行数据封装
 * 领域对象更注重领域，而DTO更注重数据
 */
public class ActivityDto {
    private Long activityId;
    // 活动名称
    private String activityName;
    // 活动描述
    private String activityDesc;
    private Date beginDateTime;
    private Date endDateTime;
    // 库存
    private Integer stockCount;
    // 每人可参与次数
    private Integer takeCount;
    // 活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启
    private Integer state;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public Date getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(Date beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
