package cn.itedus.lottery.rpc;

import cn.itedus.lottery.rpc.req.ActivityReq;
import cn.itedus.lottery.rpc.res.ActivityRes;

/**
 * 询问活动信息byID
 */
public interface IActivityBooth {

ActivityRes queryActivityById(ActivityReq req);

}
