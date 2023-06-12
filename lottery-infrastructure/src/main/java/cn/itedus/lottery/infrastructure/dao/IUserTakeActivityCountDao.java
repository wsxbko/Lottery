package cn.itedus.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.itedus.lottery.infrastructure.po.UserTakeActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 用户活动参与次数表Dao

 */
@Mapper
public interface IUserTakeActivityCountDao {

    //查询用户领取次数信息
    //这里的userTake...都是请求入参
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    //插入领取次数信息
    //为啥插入这里没注解
    void insert (UserTakeActivityCount userTakeActivityCount);

    //更新
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);
}
