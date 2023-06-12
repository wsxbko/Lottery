package cn.itedus.lottery.domain.rule.service.engine;

import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.itedus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.itedus.lottery.domain.rule.model.res.EngineResult;
import cn.itedus.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.itedus.lottery.domain.rule.model.vo.TreeRootVO;
import cn.itedus.lottery.domain.rule.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 这里主要提供决策树流程的处理过程，有点像通过链路的关系(性别、年龄)在二叉树中寻找果实节点的过程。
 * 同时提供一个抽象方法，执行决策流程的方法供外部去做具体的实现。
 */
public class EngineBase extends EngineConfig implements EngineFilter{

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        throw new RuntimeException("未实现规则引擎服务");
    }

    //引擎决策maker
    protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich,DecisionMatterReq matter){
        // 获取树根
        TreeRootVO treeRoot = treeRuleRich.getTreeRoot();
        // 获取 树节点ID -> 子节点 的Map
        Map<Long,TreeNodeVO> treeNodeMap = treeRuleRich.getTreeNodeMap();

        // 获取规则树根ID
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNodeVO treeNodeInfo = treeNodeMap.get(rootNodeId);

        //节点类型[NodeType]
        while(Constants.NodeType.STEM.equals(treeNodeInfo.getNodeType())){
            String ruleKey = treeNodeInfo.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);

            String matterValue = logicFilter.matterValue(matter);
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLineInfoList());
            //获取下一个
            treeNodeInfo = treeNodeMap.get(nextNode);
            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(), matter.getUserId(), matter.getTreeId(), treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNodeInfo;


    }



}
