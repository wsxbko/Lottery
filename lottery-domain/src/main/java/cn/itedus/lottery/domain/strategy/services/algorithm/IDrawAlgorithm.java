package cn.itedus.lottery.domain.strategy.services.algorithm;

import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;

import java.util.List;

/**
 * 抽奖的接口
 * 返回类型为String，目的在于返回抽奖结果
 * strategyId：抽奖策略
 * excludeAwardIds： 排除掉已经不能作为抽奖的奖品ID
 *
 */
public interface IDrawAlgorithm {

    void initRateTable(Long strategyId,List<AwardRateInfo> awardRateInfoList);

    //根据抽奖策略id和需要排除的奖品，返回结果
    String randomDraw(Long strategyId, List<String> excludeAwardIds);

    boolean isExistRateTuple(Long strategyId);
}
