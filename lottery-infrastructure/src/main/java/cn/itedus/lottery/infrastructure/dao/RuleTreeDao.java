package cn.itedus.lottery.infrastructure.dao;

import cn.itedus.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则树配置DAO
 */
@Mapper
public interface RuleTreeDao {
    /**
     * 规则树查询
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树简要信息查询
     */
    RuleTree queryTreeSummaryInfo(Long treeId);

}
