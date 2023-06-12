package cn.itedus.lottery.domain.strategy.model.aggregutes;


import cn.itedus.lottery.domain.strategy.model.vo.StrategyBriefVO;
import cn.itedus.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;

import java.util.List;

public class StrategyRich {
    // 策略ID
    private Long strategyId;
    // 策略配置
    private StrategyBriefVO strategy;
    // 策略明细
    /**
     *   private String id;
     *   private Long strategyId;
     *   private String awardId;
     *   private String awardCount;
     *   private BigDecimal awardRate;
     *   private java.sql.Timestamp createTime;
     *   private java.sql.Timestamp updateTime;
     */
    private List<StrategyDetailBriefVO> strategyDetailList;

    private Long awardSurplusCount;

    public StrategyRich() {
    }

    public StrategyRich(Long strategyId, StrategyBriefVO strategy, List<StrategyDetailBriefVO> strategyDetailList, Long awardSurplusCount) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
        this.awardSurplusCount = awardSurplusCount;
    }

    public StrategyRich(Long strategyId, StrategyBriefVO strategy, List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public StrategyBriefVO getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyBriefVO strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetailBriefVO> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }

    public Long getAwardSurplusCount() {
        return awardSurplusCount;
    }

    public void setAwardSurplusCount(Long awardSurplusCount) {
        this.awardSurplusCount = awardSurplusCount;
    }
}
