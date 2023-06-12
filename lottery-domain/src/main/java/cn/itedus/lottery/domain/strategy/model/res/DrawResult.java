package cn.itedus.lottery.domain.strategy.model.res;

import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * 服务端回复，除了用户Id和抽奖策略id还有礼品信息
 */
public class DrawResult {
    // 用户ID
    private String uId;

    // 策略ID
    private Long strategyId;

//    // 奖品ID
//    private String rewardId;
//
//    // 奖品名称
//    private String awardName;

    /**
     * 把上面的奖品信息封装在枚举类里
     * 中奖状态：0未中奖、1已中奖、2兜底奖 Constants.DrawState
     */

    /**
     * 中奖奖品信息
     */
    private DrawAwardVO drawAwardVO;//比上一个分支多了一个这个

    private Integer drawState = Constants.DrawState.FAIL.getCode();


    public DrawResult() {
    }

    public DrawResult(String uId, Long strategyId, Integer drawState, DrawAwardVO drawAwardVO) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawAwardVO = drawAwardVO;
        this.drawState = drawState;
    }

    public DrawResult(String uId, Long strategyId, Integer drawState) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getDrawState() {
        return drawState;
    }

    public void setDrawState(Integer drawState) {
        this.drawState = drawState;
    }

    public DrawAwardVO getDrawAwardInfo() {
        return drawAwardVO;
    }

    public void setDrawAwardInfo(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }
}
