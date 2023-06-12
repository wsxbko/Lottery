package cn.itedus.lottery.domain.strategy.services.draw;
/**
 *模板模式的使用主要在于对领域模块 lottery-domain.strategy 中 draw 抽奖包下的类处理。
 * DrawConfig：配置抽奖策略，SingleRateRandomDrawAlgorithm、EntiretyRateRandomDrawAlgorithm
 * DrawStrategySupport：提供抽奖策略数据支持，便于查询策略配置、奖品信息。通过这样的方式隔离职责。
 * AbstractDrawBase：抽象类定义模板方法流程，在抽象类的 doDrawExec 方法中，处理整个抽奖流程，并提供在流程中需要使用到的抽象方法，由 DrawExecImpl 服务逻辑中做具体实现。
 *
 */