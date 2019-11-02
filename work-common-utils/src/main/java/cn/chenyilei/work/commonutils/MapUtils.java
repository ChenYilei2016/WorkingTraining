package cn.chenyilei.work.commonutils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/26 14:49
 */
public class MapUtils {

    public static <K,V> Map<K,V> toMap(List<K> keyList, List<V> valueList){
        System.err.println(keyList);
        System.err.println(valueList);
        if(null == keyList || valueList == null){
            throw new NullPointerException("MapUtils toMap 参数错误,不能为null!");
        }
        if(keyList.size() != valueList.size()){
            throw new RuntimeException("MapUtils toMap 参数错误,数量不相等!");
        }
        Map map = new HashMap();
        for (int i = 0; i < keyList.size(); i++) {
            map.put(keyList.get(i),valueList.get(i));
        }
        return map;
    }

}
