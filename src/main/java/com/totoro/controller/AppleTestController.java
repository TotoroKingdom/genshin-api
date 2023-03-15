package com.totoro.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.totoro.constants.Result;
import com.totoro.exception.ServiceException;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;
import com.totoro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试接口
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@EnableScheduling
@RestController
@RequestMapping("test")
public class AppleTestController {

    @Resource
    private UserService userService;

    ConcurrentHashMap map = new ConcurrentHashMap<String,String>();
    ExecutorService executor = Executors.newFixedThreadPool(20);

    @RequestMapping("clear")
    public Result clear(@RequestParam("id") Long id, HttpServletRequest request){

        if (map.containsKey(id)){
            return Result.success(id+"limit NOT OK");
        }

        if (ObjectUtil.isNotNull(id)){
            System.out.println(id);
            long timeMillis = System.currentTimeMillis();
            map.put(id, timeMillis);
        }
        return Result.success(id+"limit OK");
    }

    @Scheduled(cron = "* 0/1 * * * ?")
    public void clearMap(){
        System.out.println("开始释放");
        long current = System.currentTimeMillis();
        map.forEach((k,v) ->{
            Long time = (Long) map.get(k);
            if ( (current - time)/1000 > 60 ){
                map.remove(k);
            }
        });
        System.out.println("结束释放");
    }


    @RequestMapping("limit")
    public Result limit(@RequestParam("id") Long id, HttpServletRequest request){

        if (map.containsKey(id)){
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName()+"休眠10秒钟");
                    map.remove(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            executor.submit(thread);
            return Result.success(id+"limit NOT OK");
        }

        if (ObjectUtil.isNotNull(id)){
            System.out.println(id);
            map.put(id,id);
        }
        return Result.success(id+"limit OK");
    }

    /**
     * 全局异常测试
     * @param user
     * @return
     */
    @RequestMapping("union")
    public Result union(@RequestBody User user){

        if (true){
            throw new ServiceException("异常测试");
        }

        IPage<UserVo> userVoList = userService.findUserVoList(user);

        return Result.success(userVoList);
    }

    /**
     * 校验注解测试
     * @param user
     * @return
     */
    @RequestMapping("add")
    public Result add(@RequestBody @Validated User user){
        userService.add(user);
        return Result.success("新增成功");
    }

    /**
     * 权限测试1
     * @return
     */
    @GetMapping("test1")
    public Result<User> test1(){
        User user = new User();
        user.setId(1l);
        user.setNickname("老黑");
        return Result.success(user);
    }

    /**
     * 权限测试2
     * @return
     */
    @GetMapping("pre/auth")
    @PreAuthorize("hasAuthority('admin_key')")
    public String preAuth(){
        return "admin-key";
    }
}
