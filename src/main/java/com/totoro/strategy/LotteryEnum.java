package com.totoro.strategy;

import com.totoro.exception.ServiceException;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description:
 */
public enum LotteryEnum {

    CHARACTER_ONE("00","IndefiniteLotteryService"),
    CHARACTER_TWO("01","CharacterLotteryService"),
    INDEFINITE("02","CharacterLotteryService"),
    WEAPON("03","WeaponLotteryService");

    /**
     * 00-常驻 01-角色活动1 02-角色活动2 03-武器活动
     */
    private String code;

    /**
     * 策略
     */
    private String strategy;

    LotteryEnum(String code, String strategy) {
        this.strategy = strategy;
        this.code = code;
    }

    public static String getStrategy(String code) {
        for (LotteryEnum value : LotteryEnum.values()) {
            if (value.getCode().equals(code)){
                return value.getStrategy();
            }
        }
        throw new ServiceException("没有该类型的策略");
    }

    public String getStrategy(){
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LotteryEnum{" +
                "strategy='" + strategy + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
