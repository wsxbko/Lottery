//package cn.itedus.lottery.domain.strategy.services.draw.impl;
//
//import cn.itedus.lottery.domain.strategy.model.aggregutes.StrategyRich;
//import cn.itedus.lottery.domain.strategy.model.req.DrawReq;
//import cn.itedus.lottery.domain.strategy.model.res.DrawResult;
//import cn.itedus.lottery.domain.strategy.repository.IStrategyRepository;
//import cn.itedus.lottery.domain.strategy.services.algorithm.IDrawAlgorithm;
//import cn.itedus.lottery.domain.strategy.services.draw.DrawBase;
//import cn.itedus.lottery.domain.strategy.services.draw.IDrawExec;
//
//import cn.itedus.lottery.infrastructure.po.Award;
//import cn.itedus.lottery.infrastructure.po.Strategy;
//import cn.itedus.lottery.infrastructure.po.StrategyDetail;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 抽奖实现类
// *      策略仓库接口
// */
//
///**
// * ---------------------------------------------
// * type2
// * 使用模板模式优化类 DrawExecImpl 抽奖过程方法实现
// * 以抽象类 AbstractDrawBase 编排定义流程
// * 定义抽象方法由类 DrawExecImpl 做具体实现的方式进行处理
// */
//
//
////@Service("drawExec")
//public class DrawExecImplOld extends DrawBase implements IDrawExec {
//
//    private Logger logger = LoggerFactory.getLogger(DrawExecImplOld.class);
//
//    @Resource
//    private IStrategyRepository strategyRepository;
//
//    @Override
//    public DrawResult doDrawExec(DrawReq req) {
//        logger.info("执行策略抽奖开始，strategyId：{}", req.getStrategyId());
//
//        // 获取抽奖策略配置数据,通过StrategyRich和Strategy获得checkAndInitRateData()的参数，抽象来说就是调用了DrawBase，DrawBase在分层上属于提供标准执行层
//        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
//        Strategy strategy=strategyRich.getStrategy();//获取策略
//        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();
//
//        // 初始化
//        checkAndInitRateData(req.getStrategyId(),strategy.getStrategyMode(),strategyRich.getStrategyDetailList());
//        // 根据策略方式抽奖
//        IDrawAlgorithm drawAlgorithm= drawAlgorithmMap.get(strategy.getStrategyMode());
//        String awardId = drawAlgorithm.randomDraw(req.getStrategyId(),new ArrayList<>());
//
//        //获取奖品信息
//        Award award = strategyRepository.queryAwardInfo(awardId);
//
//        logger.info("执行策略抽奖完成，中奖用户：{} 奖品ID：{} 奖品名称：{}", req.getuId(), awardId, award.getAwardName());
//
//        // 封装结果
////        return new DrawResult(req.getuId(), req.getStrategyId(), awardId, award.getAwardName());
//        return null;
//    }
//}
