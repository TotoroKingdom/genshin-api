package com.totoro;

import com.totoro.mapper.PrayMapper;
import com.totoro.pojo.Card;
import com.totoro.pojo.Pray;
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
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Card card = prayService.push();
            cards.add(card);

        }

        cards.forEach(System.out::println);
    }

    @Test
    void insertPray(){
        Pray pray = new Pray();
        pray.setUserId(1l);
        prayMapper.insert(pray);
    }


}
