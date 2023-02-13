package com.totoro.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.totoro.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: totoro
 * @createDate: 2023 02 13 22 48
 * @description:
 **/
@SpringBootTest
public class SpringUtilsTest {

    @Test
    void getBean(){
        User object = new User();
        String simpleName = object.getClass().getSimpleName().toLowerCase();
        String mapperName = simpleName+"Mapper";
        BaseMapper mapper = (BaseMapper) SpringUtils.getBean(mapperName);
        QueryWrapper<Object> wrapper = new QueryWrapper<>().eq("username", "field");
        List list = mapper.selectList(wrapper);
        System.out.println(list);
    }
}
