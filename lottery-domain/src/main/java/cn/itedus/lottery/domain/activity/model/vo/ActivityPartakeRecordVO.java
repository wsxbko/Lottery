package cn.itedus.lottery.domain.activity.model.vo;

/**
 * 活动参与记录
 */
public class ActivityPartakeRecordVO {

    //用户ID
    private String uId;

    //activity 活动ID
    private Long activityId;

    //库存
    private Integer stockCount;

    //活动剩余库存
    private Integer stockSurplusCount;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getStockSurplusCount() {
        return stockSurplusCount;
    }

    public void setStockSurplusCount(Integer stockSurplusCount) {
        this.stockSurplusCount = stockSurplusCount;
    }

    @Override
    public String toString() {
        return "ActivityPartakeRecordVO{" +
                "uId='" + uId + '\'' +
                ", activityId=" + activityId +
                ", stockCount=" + stockCount +
                ", stockSurplusCount=" + stockSurplusCount +
                '}';
    }
}
