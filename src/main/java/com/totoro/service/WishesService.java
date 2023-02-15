package com.totoro.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.pojo.Wishes;
import com.totoro.pojo.vo.LeftTimeVo;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 58
 * @description:
 **/
public interface WishesService {

    /**
     * 新增
     * @param wishes
     * @return
     */
    int add(Wishes wishes);

    /**
     * 更新
     * @param wishes
     * @return
     */
    int renew(Wishes wishes);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Wishes findById(Long id);

    /**
     * 活动倒计时
     * @param id
     * @return
     */
    LeftTimeVo countdown(Long id);

    /**
     * 分页查询
     * @param wishes
     * @return
     */
    Page<Wishes> page(Wishes wishes);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Long id);
}
