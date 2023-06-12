package cn.itedus.lottery.domain.award.services.factory;

import cn.itedus.lottery.domain.award.services.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

@Service
public class DistributionGoodsFactory extends GoodsConfig{
    /**
     * 返回对应的接口变量
     */

    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }
}
