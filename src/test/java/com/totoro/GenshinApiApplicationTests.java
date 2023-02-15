package com.totoro;

import com.totoro.mapper.PrayMapper;
import com.totoro.pojo.Card;
import com.totoro.pojo.User;
import com.totoro.pojo.auth.LoginUser;
import com.totoro.service.PrayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest
@TestInstance(PER_CLASS)
class GenshinApiApplicationTests {

    @Resource
    private PrayService prayService;

    @Resource
    private PrayMapper prayMapper;

    void login(){
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("admin");
        Set<String> permissions = new HashSet<>();
        LoginUser loginUser = new LoginUser(user,permissions);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    void contextLoads() {
        login();
        int count = 0;
        int i = 0;
        ArrayList<String> cards = new ArrayList<>();
        for (; i < 10; i++) {
            Card card = prayService.push();
            cards.add(card.getCardName());
            String cardName = card.getCardName();
            if (cardName.equals("三星物品")){
                count++;
            }

        }
        cards.forEach(System.out::println);
        System.out.println("count=" + count);
    }




}
