package cn.itedus.lottery.domain.strategy.services.draw;

import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.domain.strategy.services.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DrawConfig {
    //接口变量，一个抽奖策略对应一个
    @Resource
    private IDrawAlgorithm entiretyRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected static Map<Integer,IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(Constants.StrategyMode.ENTIRETY.getCode(),entiretyRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(Constants.StrategyMode.SINGLE.getCode(), singleRateRandomDrawAlgorithm);
    }
}
