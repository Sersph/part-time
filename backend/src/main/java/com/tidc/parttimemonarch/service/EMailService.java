package com.tidc.parttimemonarch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send() throws MessagingException {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);


        mimeMessageHelper.setFrom("18934086807@163.com");
        mimeMessageHelper.setTo("2675835744@qq.com");

        mimeMessageHelper.setSubject("兼职君提醒您");

//        mimeMessageHelper.setText("兼职君提醒您兼职君提醒您兼职君提醒您兼职君提醒您兼职君提醒您兼职君提醒您兼职君提醒您兼职君提醒您兼职君提醒您");

        String html = "<html>\n" +
                "\t<body style=\"background: #555;\">\n" +
                "\t\t<div style=\"margin: 0 auto; width: 700px; background: #fff; padding:10px 50px; box-sizing: border-box;\">\n" +
                "\t\t\t\n" +
                "\t\t\t<div style=\"\n" +
                "\t\t\t\twidth: 100px;\n" +
                "\t\t\t\theight: 100px;\n" +
                "\t\t\t\tbackground-image: url('https://mmbiz.qpic.cn/mmbiz_jpg/Vvf7kib5uRQFwL5ZRwDaibf4wftWiaH1bILblPn6rBibia3dUKckn8RSUpFaN9QIib7LGSW1utIVLPbhic60D7Kl96RDQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1');\n" +
                "\t\t\t\tbackground-size: cover;\n" +
                "\t\t\t\">\n" +
                "\t\t\t</div>\n" +
                "\t\t\n" +
                "\t\t\t<div>\n" +
                "\t\t\t\t<h2 style=\"color: #0AF;\">亲爱的兼职君用户，您好:</h2>\n" +
                "\t\t\t\t<p style=\"text-indent: 2em; line-height: 30px;\">\n" +
                "\t\t\t\t\t欢迎您成为兼职君注册用户，为了您能随时接收招聘信息，邀请您完成邮箱验证。\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t\t<p style=\"text-indent: 2em;\">\n" +
                "\t\t\t\t\t本次请求的邮件验证码是：<span style=\"color: #0AF;\">674130</span>\n" +
                "\t\t\t\t\t<p style=\"text-indent: 2em;\">本验证码5分钟内有效，请及时输入。</p>\n" +
                "\t\t\t\t</p>\n" +
                "\n" +
                "\t\t\t\t<p style=\"text-indent: 2em; line-height: 30px;\">\n" +
                "\t\t\t\t\t为保证账号安全，请勿泄漏此验证码。\n" +
                "\t\t\t\t</p style=\"text-indent: 2em; line-height: 30px;\">\n" +
                "\t\t\t\t<p style=\"text-indent: 2em; line-height: 30px;\">\n" +
                "\t\t\t\t\t祝在【兼职君】收获人才！\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t\t<p style=\"text-indent: 2em; line-height: 30px;\">\n" +
                "\t\t\t\t这是一封自动发送的邮件，请不要直接回复!\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</div>\t\n" +
                "\n" +
                "\t\t</div>\n" +
                "\t</body>\n" +
                "</html>";

        mimeMessageHelper.setText(html);
        this.javaMailSender.send(mimeMessage);
    }
}
