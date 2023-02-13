package com.totoro.utils;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.pojo.dto.Params;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
public class ParamsUtils {

    public static Page getPage(){
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servlet = (ServletRequestAttributes) attributes;
        HttpServletRequest request = servlet.getRequest();
        Long current = Convert.toLong(request.getParameter("current"));
        Long size = Convert.toLong(request.getParameter("size"));
        Page page = Page.of(current, size);
        return page;
    }

    public static Params getParams(){
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servlet = (ServletRequestAttributes) attributes;
        HttpServletRequest request = servlet.getRequest();

        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");

        Params params = new Params();
        params.setBeginTime(beginTime);
        params.setEndTime(endTime);
        return params;
    }
}
