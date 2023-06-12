package cn.itedus.lottery.domain.strategy.services.algorithm.impl;

import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.itedus.lottery.domain.strategy.services.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Component("DefaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        BigDecimal differenceDenominator=BigDecimal.ZERO;//计算当前奖品的概率总和

        //排除不在抽奖范围的奖品后的奖品ID集合
        List<AwardRateInfo> differenceAwardRateList = new ArrayList<>();
        List<AwardRateInfo> awardRateIntervalValList = awardRateInfoMap.get(strategyId);
        for(AwardRateInfo awardRateInfo:awardRateIntervalValList){
            String awardId=awardRateInfo.getAwardId();
            if(excludeAwardIds.contains(awardId)){continue;}
            differenceAwardRateList.add(awardRateInfo);
            /**
             * public BigDecimal add (BigDecimal augend)
             *
             * 返回 BigDecimal其值为 (this + augend) ，其比例为 max(this.scale(), augend.scale()) 。
             */

            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        if (differenceAwardRateList.size()==0) return "";
        if (differenceAwardRateList.size()==1) return differenceAwardRateList.get(0).getAwardId();

        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100)+1;

        String awardId ="";
        int cursorVal=0;
        for(AwardRateInfo awardRateInfo:differenceAwardRateList){
            int rateVal=awardRateInfo.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if (randomVal <= (cursorVal + rateVal)) {
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }
        return awardId;
    }
}
