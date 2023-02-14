package com.totoro;

import com.totoro.mapper.PrayMapper;
import com.totoro.pojo.Card;
import com.totoro.service.PrayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest
@TestInstance(PER_CLASS)
class GenshinApiApplicationTests {

    @Resource
    private PrayService prayService;

    @Resource
    private PrayMapper prayMapper;

    @Test
    void contextLoads() {
        int count = 0;
        int i = 0;
        ArrayList<String> cards = new ArrayList<>();
        for (; i < 100000; i++) {
            Card card = prayService.push();
            cards.add(card.getCardName());
            String cardName = card.getCardName();
            if (cardName.equals("三星物品")){
                count++;
            }

        }
        cards.forEach(System.out::println);
        System.out.println("count=" + count);
        System.out.println("不中奖概率=" + count/i*1.0*100 + "%");
        System.out.println("中奖概率=" + (i-count)/i*1.0*100 + "%");
    }




}
