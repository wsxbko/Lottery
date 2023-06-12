package cn.itedus.lottery.domain.award.services.goods.impl;

import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.domain.award.model.req.GoodsReq;
import cn.itedus.lottery.domain.award.model.res.DistributionRes;
import cn.itedus.lottery.domain.award.services.goods.DistributionBase;
import cn.itedus.lottery.domain.award.services.goods.IDistributionGoods;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.stereotype.Component;

/**
 * 实现发送奖品CouponGoods
 */

@Component
public class CouponGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        // 模拟调用优惠券发放接口
        logger.info("模拟调用优惠券发放接口 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());
        super.updateUserAwardState(req.getuId(),req.getOrderId(),req.getAwardId(), Constants.AwardState.SUCCESS.getCode(),Constants.AwardState.SUCCESS.getInfo());
        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.CouponGoods.getCode();
    }
    //由于抽奖系统并没有真的与外部系统对接，所以在例如优惠券、兑换码、实物发货上只能通过模拟的方式展示。另外四种发奖方式基本类似，可以参考源码。
}
