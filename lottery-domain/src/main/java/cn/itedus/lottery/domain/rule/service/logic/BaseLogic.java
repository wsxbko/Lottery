package cn.itedus.lottery.domain.rule.service.logic;

import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.itedus.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * 规则基础抽象类
 */
public abstract class BaseLogic implements LogicFilter {

    /**
     * 逻辑决策器
     * @param matterValue           决策值
     * @param treeNodeLineInfoList  决策节点
     * @return                      下一个节点Id
     */
    @Override
    public Long filter (String matterValue, List<TreeNodeLineVO>treeNodeLineInfoList){
        for(TreeNodeLineVO nodeLine: treeNodeLineInfoList){
            //决定是哪个并且返回
            if(decisionLogic(matterValue,nodeLine)){
                return nodeLine.getNodeIdTo();
            }
        }
        return Constants.Global.TREE_NULL_NODE;
    }

    /**
     * 获取规则对比值
     * @param decisionMatter        决策物料
     * @return 比对值
     */
    @Override
    public abstract String matterValue(DecisionMatterReq decisionMatter);

    private boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLine){
        //限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围]
        switch (nodeLine.getRuleLimitType()){
            case Constants.RuleLimitType.EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GT:
                //matter值和限定值作比较
                return Double.parseDouble(matterValue)>Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getRuleLimitValue());
            default:
                return false;

        }

    }
}
