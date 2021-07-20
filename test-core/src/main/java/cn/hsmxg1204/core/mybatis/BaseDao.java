package cn.hsmxg1204.core.mybatis;

import cn.hsmxg1204.core.mapper.DataMapper;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-03-14 10:15
 */
public abstract class BaseDao {

    @Resource
    DataMapper dataMapper;

//    protected <T> T select(Sql sql,Class<T> clz){
//        List<T> ls = selectList(sql,clz);
//        if(ls!=null && ls.size()> 1){
//            throw new MyException("查询出多个结果");
//        }
//        if(ls != null && ls.size() > 0){
//            return selectList(sql,clz).get(0);
//        }
//        return null;
//    }

//    protected <T> List<T> selectList(Sql sql, Class<T> clz) {
//        List<Map<String,Object>> select = dataMapper.select(sql.getMaps(),sql.toSql());
//        return MyUtils.MapToObj(select,clz);
//    }
}
