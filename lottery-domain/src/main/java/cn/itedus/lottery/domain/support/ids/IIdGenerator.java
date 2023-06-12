package cn.itedus.lottery.domain.support.ids;

/**
 * 定义生成ID的策略接口。
 * RandomNumeric、ShortCode、SnowFlake，是三种生成ID的策略。
 *
 * 目的：获取ID
 * 1. 雪花算法，用于生成单号
 * 2. 日期算法，用于生成活动编号类，特性是生成数字串较短，但指定时间内不能生成太多
 * 3. 随机算法，用于生成策略ID
 */
public interface IIdGenerator {
    long nextId();
}
