package com.totoro.email;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
public interface Email {

    /**
     * 发送邮件
     * @param receiver
     * @param subject
     * @param text
     */
    void send(String receiver, String subject, String text);

    /**
     * 发送html邮件
     * @param receiver
     * @param subject
     * @param text
     */
    void send(String receiver, String subject, String text, String htmlUrl);

    /**
     * 构造模板邮件
     * @return
     */
    String buildContent(String htmlUrl, String text);
}
