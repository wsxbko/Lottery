//package cn.itedus.lottery.domain.strategy.services.draw;
//
//import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;
//import cn.itedus.lottery.domain.strategy.services.algorithm.IDrawAlgorithm;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DrawBase extends DrawConfig{
//
//    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList){
//        //strategyMode是什么
//        //    策略方式「1:单项概率、2:总体概率」
//        //    private Integer strategyMode;
//        /**
//         * 单项概率就是随机生成一个 散列表的 下标 看那有没有奖品，所以直接返回
//         */
//        if(strategyMode==1) return;
//        /**
//         * extends DrawConfig 的 drawAlgorithmMap<Integer,接口 >
//         */
//        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);//获取接口
//        //通过获取接口变量来获取接口方法
//        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
//        if (existRateTuple) return;
//
//        /**
//         * 通过`策略表`来初始化`奖品表`
//         */
//        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
//        for(StrategyDetail strategyDetail: strategyDetailList){
//            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
//        }
//        drawAlgorithm.initRateTable(strategyId,awardRateInfoList);
//    }
//
//}
