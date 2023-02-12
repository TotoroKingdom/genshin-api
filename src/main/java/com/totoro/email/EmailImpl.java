package com.totoro.email;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Slf4j
@Service
public class EmailImpl implements Email {

    @javax.annotation.Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${register.callback.url}")
    private String registerCallbackUrl;

    @Override
    public void send(String receiver, String subject, String text){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setSubject(subject);
            helper.setTo(receiver);
            helper.setText(text);
            javaMailSender.send(message);
            log.info("邮件发送成功");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    @Override
    public void send(String receiver, String subject, String text, String htmlUrl) {

        text = buildContent(htmlUrl, text);

        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setSubject(subject);
            helper.setTo(receiver);
            helper.setText(text,true);
            javaMailSender.send(message);
            log.info("邮件发送成功");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    @Override
    public String buildContent(String htmlUrl, String text) {
        if (StrUtil.isEmpty(htmlUrl)){
            htmlUrl = "static/register.ftl";
        }

        Resource resource = new ClassPathResource(htmlUrl);
        String line = "";
        StringBuffer buffer = new StringBuffer();
        try(InputStream inputStream = resource.getInputStream();
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            while ((line = fileReader.readLine()) != null){
                buffer.append(line);
            }

        } catch (IOException e) {
            log.info("读取邮件模板失败{}",e);
            e.printStackTrace();
        }

        return MessageFormat.format(buffer.toString(), text);
    }
}
