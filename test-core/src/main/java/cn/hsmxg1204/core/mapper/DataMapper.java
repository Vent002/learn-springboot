package cn.hsmxg1204.core.mapper;

import cn.hsmxg1204.core.mybatis.MapperProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-03-14 10:20
 */
//@Service
public interface DataMapper {
    @SelectProvider(type = MapperProvider.class,method = "select")
    List<Map<String,Object>> select(Map<String,Object> map, String sql);


}
