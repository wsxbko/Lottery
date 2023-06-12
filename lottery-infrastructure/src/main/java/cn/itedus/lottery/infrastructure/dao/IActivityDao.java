package cn.itedus.lottery.infrastructure.dao;

import cn.itedus.lottery.domain.activity.model.vo.AlterStateVO;
import cn.itedus.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * dao层 就是一个类 DATA ACCESS OBJECT
 * 把访问数据库的代码封装起来 增删改查，不涉及业务逻辑
 * DAO在数据库与业务逻辑（Service）之间
 */
//没加mapper注解

@Mapper
public interface IActivityDao {

void insert(Activity req);

Activity queryActivityById(Long activityId);

int alterState(AlterStateVO alterStateVO);

    /**
     * 扣减活动库存
     * @param activityId
     * @return
     */
    int subtractionActivityStock(Long activityId);

    /**
     * 扫描待处理的活动列表，状态为：通过、活动中
     *
     * @param id ID
     * @return 待处理的活动集合
     */
    List<Activity> scanToDoActivityList(Long id);


    /**
     * 更新用户领取活动后，活动库存
     *
     * @param activity  入参
     */
    void updateActivityStock(Activity activity);


}
