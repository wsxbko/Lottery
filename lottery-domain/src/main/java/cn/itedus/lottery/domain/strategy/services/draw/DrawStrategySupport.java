package cn.itedus.lottery.domain.strategy.services.draw;

import cn.itedus.lottery.domain.strategy.model.aggregutes.StrategyRich;
import cn.itedus.lottery.domain.strategy.model.vo.AwardBriefVO;
import cn.itedus.lottery.domain.strategy.repository.IStrategyRepository;


import javax.annotation.Resource;

/**
 * 抽奖策略数据支撑，一些通用的数据服务
 *  -查询策略配置信息
 *  -中奖详情
 */
public class DrawStrategySupport extends DrawConfig{
    @Resource
    protected IStrategyRepository strategyRepository;

    //-查询策略配置信息
    //为什么不直接使用strategyRepository.queryStrategyRich(strategyId)？
    protected StrategyRich queryStrategyRich(Long strategyId){
        return strategyRepository.queryStrategyRich(strategyId);
    }
    //-中奖详情
    protected AwardBriefVO queryAwardInfoByAwardId(String awardId){
      return strategyRepository.queryAwardInfo(awardId);
    }
}
