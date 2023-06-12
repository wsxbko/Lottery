package cn.itedus.lottery.domain.rule.service.logic;

import cn.itedus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.itedus.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * 规则过滤器 接口
 *
 * 这一部分定义了适配的通用接口，逻辑决策器、获取决策值，
 * 让每一个提供决策能力的节点都必须实现此接口，保证统一性。
 */
public interface LogicFilter {
    /**
     * 逻辑决策器
     * @param matterValue           决策值
     * @param treeNodeLineInfoList  决策节点
     * @return                      下一个节点Id
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList);

    /**
     * 获取决策值
     * @param decisionMatter        决策物料
     * @return                      决策值
     */
    String matterValue(DecisionMatterReq decisionMatter);
}
