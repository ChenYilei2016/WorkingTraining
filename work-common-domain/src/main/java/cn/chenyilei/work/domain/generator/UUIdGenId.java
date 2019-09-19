package cn.chenyilei.work.domain.generator;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * @KeySql(genId = UUIdGenId.class)
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/18 11:02
 */
public class UUIdGenId implements GenId<String>  {

    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
