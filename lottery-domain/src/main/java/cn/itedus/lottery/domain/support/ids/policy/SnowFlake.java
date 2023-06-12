package cn.itedus.lottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import cn.itedus.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowflake;

    @PostConstruct
    public void init(){
        long workerId;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId=NetUtil.getLocalhostStr().hashCode();
        }
        //why
        workerId = workerId >> 16 & 31;

        long dataCenterId = 1L;
        snowflake = IdUtil.createSnowflake(workerId,dataCenterId);

    }

    @Override
    public long nextId() {
        return 0;
    }
}
