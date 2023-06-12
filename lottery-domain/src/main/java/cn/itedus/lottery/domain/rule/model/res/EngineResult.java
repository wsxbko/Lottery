package cn.itedus.lottery.domain.rule.model.res;

/**
 * （筛选用户身份标签，找到符合参与的活动号 后的）决策结果
 */
public class EngineResult {

    /** 执行结果 */
    private boolean isSuccess;

    /** 用户ID */
    private String userId;

    /** 规则树ID */
    private Long treeId;

    /** 果实节点ID */
    private Long nodeId;

    /** 果实节点值 */
    private String nodeValue;

    public EngineResult() {
    }

    public EngineResult(boolean isSuccess, String userId, Long treeId, Long nodeId, String nodeValue) {
        this.isSuccess = isSuccess;
        this.userId = userId;
        this.treeId = treeId;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }

    public EngineResult(String userId, Long treeId, Long nodeId, String nodeValue) {
        this.userId = userId;
        this.treeId = treeId;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }
}
