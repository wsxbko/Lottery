package cn.itedus.lottery.infrastructure.po;


import java.math.BigDecimal;

public class StrategyDetail {

  private String id;
  private Long strategyId;
  private String awardId;
  private String awardName;
  private String awardCount;
  private Long awardSurplusCount;
  private BigDecimal awardRate;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public Long getStrategyId() {
    return strategyId;
  }

  public void setStrategyId(Long strategyId) {
    this.strategyId = strategyId;
  }


  public String getAwardId() {
    return awardId;
  }

  public void setAwardId(String awardId) {
    this.awardId = awardId;
  }


  public String getAwardCount() {
    return awardCount;
  }

  public void setAwardCount(String awardCount) {
    this.awardCount = awardCount;
  }


  public BigDecimal getAwardRate() {
    return awardRate;
  }

  public void setAwardRate(BigDecimal awardRate) {
    this.awardRate = awardRate;
  }

  public String getAwardName() {
    return awardName;
  }

  public void setAwardName(String awardName) {
    this.awardName = awardName;
  }

  public Long getAwardSurplusCount() {
    return awardSurplusCount;
  }

  public void setAwardSurplusCount(Long awardSurplusCount) {
    this.awardSurplusCount = awardSurplusCount;
  }

  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
