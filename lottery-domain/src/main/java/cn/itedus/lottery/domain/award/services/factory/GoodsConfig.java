package cn.itedus.lottery.domain.award.services.factory;

import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.domain.award.services.goods.IDistributionGoods;
import cn.itedus.lottery.domain.award.services.goods.impl.CouponGoods;
import cn.itedus.lottery.domain.award.services.goods.impl.DescGoods;
import cn.itedus.lottery.domain.award.services.goods.impl.PhysicalGoods;
import cn.itedus.lottery.domain.award.services.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 奖品配置类
 * 把四种奖品的发奖，放到一个统一的配置文件类 Map 中，便于通过 AwardType 获取相应的对象，减少 if...else 的使用。
 */
public class GoodsConfig {
    /** 奖品发放策略组 */
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();
    /** model包下的类加入Resource */

    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;


    @PostConstruct
    public void init() {
        goodsMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }


}
