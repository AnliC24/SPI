package com.hitqz.sjtc.handler.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;


/**
 * @author windc
 * @description 对数据库时间字段进行默认处理
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @ApiOperation("实体类型公共插入默认值")
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "createTime", new Date());
        this.fillStrategy(metaObject, "modifyTime", new Date());
    }

    @ApiOperation("实体类型公共更新默认值")
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifyTime", new Date(), metaObject);
    }

}
