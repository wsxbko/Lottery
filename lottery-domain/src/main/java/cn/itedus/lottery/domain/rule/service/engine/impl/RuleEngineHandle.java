package cn.itedus.lottery.domain.rule.service.engine.impl;

import cn.itedus.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.itedus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.itedus.lottery.domain.rule.model.res.EngineResult;
import cn.itedus.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.itedus.lottery.domain.rule.repository.IRuleRepository;
import cn.itedus.lottery.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 规则引擎处理器
 */

@Service("ruleEngineHandle")
public class RuleEngineHandle extends EngineBase {
    @Resource
    IRuleRepository ruleRepository;

    @Override
    public EngineResult process(DecisionMatterReq matter){
        //决策规则树
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(matter.getTreeId());
        if(null == treeRuleRich){
            throw new RuntimeException("Tree Rule is null!");

        }
        //决策节点
        TreeNodeVO treeNodeInfo = engineDecisionMaker(treeRuleRich,matter);

        return new EngineResult(matter.getUserId(),treeNodeInfo.getTreeId(), treeNodeInfo.getTreeNodeId() ,treeNodeInfo.getNodeValue());
    }




}
