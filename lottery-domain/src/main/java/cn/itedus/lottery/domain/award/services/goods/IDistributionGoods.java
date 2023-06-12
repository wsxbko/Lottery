package cn.itedus.lottery.domain.award.services.goods;

import cn.itedus.lottery.domain.award.model.req.GoodsReq;
import cn.itedus.lottery.domain.award.model.res.DistributionRes;

/**
 * 发奖适配策略
 */
public interface IDistributionGoods {

    /**
     * 奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     * @param req 物品信息
     * @return 配送信息
     */
    DistributionRes doDistribution(GoodsReq req);

    Integer getDistributionGoodsName();
}
