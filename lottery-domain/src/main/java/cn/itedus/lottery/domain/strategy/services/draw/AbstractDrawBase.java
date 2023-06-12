package cn.itedus.lottery.domain.strategy.services.draw;

import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.domain.strategy.model.aggregutes.StrategyRich;
import cn.itedus.lottery.domain.strategy.model.req.DrawReq;
import cn.itedus.lottery.domain.strategy.model.res.DrawResult;
import cn.itedus.lottery.domain.strategy.model.vo.*;
import cn.itedus.lottery.domain.strategy.services.algorithm.IDrawAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{
    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    /**
     * 校验抽奖策略是否已经初始化到内存
     * ？private怎么启动
     */
    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList){
    if(!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)){
        return;
        }

        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        //已经初始化过，跳过
        if(drawAlgorithm.isExistRateTuple(strategyId)) return;

        //解析并初始化中奖概率到散列表
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for(StrategyDetailBriefVO strategyDetail : strategyDetailList){
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
        }
        drawAlgorithm.initRateTable(strategyId,awardRateInfoList);

    }

    /**
     * 查询不参与活动的奖品列表
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);//不在这里实现

    /**
     * 执行抽奖算法
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds);

    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId){
        if (null == awardId) {
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }
        /**
        * Award award = super.queryAwardInfoByAwardId(awardId);
        *         DrawAwardInfo drawAwardInfo = new DrawAwardInfo(award.getAwardId(), award.getAwardName());
        *         logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());
        *
        *         return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardInfo);
        */
        AwardBriefVO award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardVO drawAwardVO = new DrawAwardVO(award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());
        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardVO);
    }


    @Override
    public DrawResult doDrawExec(DrawReq req) {
        // 1. 获取抽奖策略
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();
        // 2. 校验抽奖策略是否已经初始化到内存
        this.checkAndInitRateData(strategy.getStrategyId(),strategy.getStrategyMode(),strategyRich.getStrategyDetailList());
/**
 * this的类型：哪个对象调用就是哪个对象的引用类型
 */
        // 3. 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
        List<String> excludeAwardIds = queryExcludeAwardIds(req.getStrategyId()); //代码里的参数是req.getStrategyId，有问题先查看这个，按理来说没问题

        // 4. 执行抽奖算法
        /**
         * 第二个参数 IDrawAlgorithm drawAlgorithm ，怎么获取 ，为什么要设置这个参数
         */
        String awardId = this.drawAlgorithm(req.getStrategyId(),drawAlgorithmMap.get(strategy.getStrategyMode()),excludeAwardIds);

        // 5. 包装中奖结果
        return buildDrawResult(req.getuId(), req.getStrategyId(), awardId);

    }
}
