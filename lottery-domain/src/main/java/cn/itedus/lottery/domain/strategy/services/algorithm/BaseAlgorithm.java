package cn.itedus.lottery.domain.strategy.services.algorithm;

import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 共用的算法逻辑
 * 为什么使用abstract
 * 作为共用算法，另外两个抽奖策略会extends它
 * “抽象类不能直接实例化，需要依靠子类采用向上转型的方式处理”
 * “虽然一个类的子类可以去继承任意的一个普通类，可是从开发的实际要求来讲，普通类尽量不要去继承另外一个普通类，而是去继承抽象类。”
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    private final int HASH_INCREMENT = 0x61c88647;

    // 数组初始化长度
    private final int RATE_TUPLE_LENGTH = 128;

    /**
     * 一个抽奖策略 --> 获取奖品信息和它的概率 --> 通过斐波那契函数 --> 得到散列表
     * 所以需要strategyId，奖品信息表awardRateInfoList，以及得到的散列表rateTuple
     * 使用什么样的数据结果进行封装，以前做实验会想到设置一个Class 策略，里面有strategyId、awardRateInfoList、初始化后可得rateTuple，但这样不好
     *
     * 代码用了Map的kv键值对存放信息
     */
    // 存放概率与奖品对应的散列结果，strategyId(抽奖策略id) -> rateTuple
    protected Map<Long,String[]> rateTupleMap = new ConcurrentHashMap<>();

    // 存放奖品信息
    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap=new ConcurrentHashMap<>();

    @Override    // 初始化散列表
    public void initRateTable(Long strategyId,List<AwardRateInfo> awardRateInfoList){
        //存储奖品信息
        awardRateInfoMap.put(strategyId,awardRateInfoList);
        //初始化散列表
        /**
         * public V computeIfAbsent(K key, Function mappingFunction)
         * 如果指定的键尚未与值关联，则尝试使用给定的映射函数计算其值，并将其输入此映射，除非null
         * 参数
         *     key - 与指定值关联的键
         *     mappingFunction - 计算值的函数
         * 结果
         *     与指定键关联的当前（现有或已计算）值，如果计算值为null，则为null
         *
         * ->:箭头是java8版本以后出现的lambda表达式，可以把一个函数做为一个参数传进去，正常我们都是要传一个变量或对象。
         */
        String[] rateTuple=rateTupleMap.computeIfAbsent(strategyId,k->new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        //计算每个奖品在散列表的位置
        for (AwardRateInfo awardRateInfo : awardRateInfoList) {
            //rateVal就是，假设A奖品概率为0.2，length为100，那0-20号放的是A奖品，通过号码来得到索引值
            int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();
            /**
             * 概率*模长
             * Returns a BigDecimal whose value is (this × multiplicand), with rounding according to the context settings.
             */
            // 循环填充概率范围值
            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
            }
            cursorVal += rateVal;
        }

    }


    /**
     * 计算哈希索引下标值
     * @param val
     * @return 索引
     */
    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }

    /**
     *
     */

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }


    /**
     * 生成百位随机抽奖码
     *
     * @return 随机值
     */
    protected int generateSecureRandomIntCode(int bound){
        return new SecureRandom().nextInt(bound) + 1;
    }


}
