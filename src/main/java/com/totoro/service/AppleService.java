package com.totoro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.pojo.Apple;
import com.totoro.pojo.User;

import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
public interface AppleService {

    Page<Apple> page();

}
