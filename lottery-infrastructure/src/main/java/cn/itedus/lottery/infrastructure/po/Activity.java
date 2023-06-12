package cn.itedus.lottery.infrastructure.po;


import java.sql.Timestamp;
import java.util.Date;

public class Activity {

  private Long id;
  private Long activityId;
  // 活动名称
  private String activityName;
  // 活动描述
  private String activityDesc;
  private Date beginDateTime;
  private Date endDateTime;
  // 库存
  private Integer stockCount;

  /**
   * 库存剩余
   */
  private Integer stockSurplusCount;

  /**
   * 策略ID
   */
  private Long strategyId;

  // 每人可参与次数
  private Integer takeCount;
  // 活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启
  private Integer state;
  private String creator;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getStockSurplusCount() {
    return stockSurplusCount;
  }

  public void setStockSurplusCount(Integer stockSurplusCount) {
    this.stockSurplusCount = stockSurplusCount;
  }

  public Long getStrategyId() {
    return strategyId;
  }

  public void setStrategyId(Long strategyId) {
    this.strategyId = strategyId;
  }
}
