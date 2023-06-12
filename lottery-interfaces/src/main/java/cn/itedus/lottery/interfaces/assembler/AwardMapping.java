package cn.itedus.lottery.interfaces.assembler;


import cn.itedus.lottery.domain.strategy.model.vo.DrawAwardVO;
import cn.itedus.lottery.rpc.dto.AwardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * 对象转换配置
 */

//componentModel：就是依赖注入，类似于在spring的servie层用@servie注入，那么在其他地方可以使用@Autowired取到值
//spring：使用该属性，则在其他地方可以使用@Autowired取到值

//DrawAward->Award

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardVO,AwardDTO> {
    //@Mappings——一组映射关系，值为一个数组，元素为@Mapping
    //target：目标属性，赋值的过程是把“源属性”赋值给“目标属性”
    //uId->userId(对应AwardDTO）

    @Mapping(target = "userId",source = "uId")
    @Override
    AwardDTO sourceToTarget(DrawAwardVO var1);

    @Override
    DrawAwardVO targetToSource(AwardDTO var1);



}
