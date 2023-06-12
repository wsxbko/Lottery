package cn.itedus.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.itedus.lottery.infrastructure.po.UserStrategyExport;
import cn.itedus.lottery.infrastructure.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户领取活动表DAO
 */

@Mapper
public interface IUserTakeActivityDao {
    /**
     * 插入用户领取活动信息
     * @param userTakeActivity 入参
     */
    //在需要使用数据库路由的DAO方法上加入注解
    //@DBRouter(key = "uId") key 是入参对象中的属性，用于提取作为分库分表路由字段使用
    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);

    /**
     * 锁定活动领取记录
     *
     * @param userTakeActivity  入参
     * @return                  更新结果
     */
    int lockTackActivity(UserTakeActivity userTakeActivity);

    /**
     * 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * 查询此活动ID，用户最早领取但未消费的一条记录【这部分一般会有业务流程限制，比如是否处理最先还是最新领取单，要根据自己的业务实际场景进行处理】
     *
     * @param userTakeActivity 请求入参
     * @return                 领取结果
     */
    @DBRouter
    UserTakeActivity queryNoConsumedTakeActivityOrder(UserTakeActivity userTakeActivity);

    /**
     * 更新发奖状态
     * @param userStrategyExport 发奖信息
     */
    @DBRouter
    void updateUserAwardState(UserStrategyExport userStrategyExport);

    /**
     * 更新发送MQ状态
     * @param userStrategyExport 发送消息
     */
    @DBRouter
    void updateInvoiceMqState(UserStrategyExport userStrategyExport);



}
