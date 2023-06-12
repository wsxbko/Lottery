package cn.itedus.lottery.domain.strategy.repository;

import cn.itedus.lottery.domain.strategy.model.aggregutes.StrategyRich;
import cn.itedus.lottery.domain.strategy.model.vo.AwardBriefVO;


import java.util.List;

//抽奖策略仓库
public interface IStrategyRepository {
    //查询策略详细信息
    StrategyRich queryStrategyRich(Long strategyId);
//    //查询奖品
//    Award queryAwardInfo(String awardId);
    //没有了，因为不使用infrastructure层，但是添加了Award简要信息类

    AwardBriefVO queryAwardInfo(String awardId);

    //查询无库存的奖品列表
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return           扣减结果
     */
    boolean deductStock(Long strategyId, String awardId);



}
