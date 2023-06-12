package cn.itedus.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.itedus.lottery.infrastructure.po.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户策略计算结果表DAO
 *
 * DBRouterStrategy(splitTable = true) 配置分表信息，配置后会通过数据库路由组件把sql语句添加上分表字段，比如表 user 修改为 user_003
 * @DBRouter(key = "uId") 设置路由字段
 * @DBRouter 未配置情况下走默认字段，routerKey: uId
 */

@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {
/**
 * 新增数据
 * @param userStrategyExport 用户策略
 */
@DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

/**
 * 查询数据
 * @param uId 用户ID
 * @return 用户策略
 */
@DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);

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

    /**
     * 扫描发货单 MQ 状态，把未发送 MQ 的单子扫描出来，做补偿
     *
     * @return 发货单
     */
    List<UserStrategyExport> scanInvoiceMqState();

}
