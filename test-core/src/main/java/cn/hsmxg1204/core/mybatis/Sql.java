package cn.hsmxg1204.core.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-03-12 17:19
 */
public class Sql {
    StringBuffer sql;
    List<Object> values = new ArrayList<>();

    public Sql(String s){
        sql = new StringBuffer();
        sql.append(" ");
    }

    public Sql(){
        sql = new StringBuffer();
    }

    public Sql append(String s){
        sql.append(" ").append(s).append(" ");
        return this;
    }

    public String toSql(){
        String str = sql.toString();
        int len = getValues().length;
        for (int i = 0; i < len ; i++) {
            str = str.replaceFirst("\\?","#{map."+i+"}");
        }
        return str.trim();
    }

    public Object[] getValues(){
        return values.toArray();
    }

    public Map<String,Object> getMaps(){
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            map.put(i+"",values.get(i));
//            Object o = values.get(i);
//            log.info(o)
        }
        return map;
    }
}
