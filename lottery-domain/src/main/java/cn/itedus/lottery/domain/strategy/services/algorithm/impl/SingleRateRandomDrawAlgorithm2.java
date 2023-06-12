package cn.itedus.lottery.domain.strategy.services.algorithm.impl;

import cn.itedus.lottery.domain.strategy.services.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

//@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm2 extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        String[] rateTuple=super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        int randomVal = new SecureRandom().nextInt(100)+1;
        int idx=super.hashIdx(randomVal);
        //同类的单个奖品并不需要自己的专属id，因为在一开始已经分散在散列表中了，而randomVal是随机的一个号码，直接和散列表下标配对
        String awardId = rateTuple[idx];

        if(excludeAwardIds.contains(awardId))return "没中奖";

        return awardId;
    }
}
