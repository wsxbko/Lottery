package cn.itedus.lottery.infrastructure.po;


public class Strategy {

  private Long id;
  private Long strategyId;
  private String strategyDesc;
  private Integer strategyMode;
  private Integer grantType;
  private java.sql.Timestamp grantDate;
  private String extInfo;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getStrategyId() {
    return strategyId;
  }

  public void setStrategyId(Long strategyId) {
    this.strategyId = strategyId;
  }


  public String getStrategyDesc() {
    return strategyDesc;
  }

  public void setStrategyDesc(String strategyDesc) {
    this.strategyDesc = strategyDesc;
  }


  public Integer getStrategyMode() {
    return strategyMode;
  }

  public void setStrategyMode(Integer strategyMode) {
    this.strategyMode = strategyMode;
  }


  public Integer getGrantType() {
    return grantType;
  }

  public void setGrantType(Integer grantType) {
    this.grantType = grantType;
  }


  public java.sql.Timestamp getGrantDate() {
    return grantDate;
  }

  public void setGrantDate(java.sql.Timestamp grantDate) {
    this.grantDate = grantDate;
  }


  public String getExtInfo() {
    return extInfo;
  }

  public void setExtInfo(String extInfo) {
    this.extInfo = extInfo;
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
