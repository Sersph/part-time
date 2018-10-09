package com.tidc.parttimemonarch.util;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EMailUtli {

    private static final String FROM = "18934086807@163.com";


    public static boolean enterpriseSignInVerificationCode(String eMail, String code, JavaMailSender javaMailSender){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String message = "<html>\n" +
                "\t<body style=\"background: #555;\">\n" +
                "\t\t<div style=\"margin: 0 auto; width: 700px; background: url(http://img3.duitang.com/uploads/item/201511/19/20151119163813_cAZYS.thumb.700_0.jpeg); padding:10px 50px; box-sizing: border-box;\">\n" +
                "\t\t\t\n" +
                "\t\t\t<div style=\"width: 200px;height: 100px;\">\n" +
                "\t\t\t\t<h1 style=\"color: #0AF; font-family: Algerian; font-size: 50px;\">TIDC</h1>\n" +
                "\t\t\t</div>\n" +
                "\t\t\n" +
                "\t\t\t<div style=\"font-size: 18px;\">\n" +
                "\t\t\t\t<h2 style=\"color: #0AF;\">亲爱的兼职君用户，您好:</h2>\n" +
                "\t\t\t\t<p style=\"text-indent: 2em; line-height: 30px;\">\n" +
                "\t\t\t\t\t欢迎您成为兼职君注册用户，为了您能随时接收招聘信息，邀请您完成邮箱验证。\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t\t<p style=\"\">\n" +
                "\t\t\t\t\t本次请求的邮件验证码是：<span style=\"color: #0AF; border-bottom:1px dashed #0AF;\">" + code +"</span>\n" +
                "\t\t\t\t\t<p style=\"\">本验证码5分钟内有效，请及时输入。</p>\n" +
                "\t\t\t\t</p>\n" +
                "\n" +
                "\t\t\t\t<p style=\"line-height: 30px;\">\n" +
                "\t\t\t\t\t为保证账号安全，请勿泄漏此验证码。\n" +
                "\t\t\t\t</p style=\"line-height: 30px;\">\n" +
                "\t\t\t\t<p style=\"line-height: 30px;\">\n" +
                "\t\t\t\t\t祝在【兼职君】收获人才！\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t\t<p style=\"line-height: 30px;\">\n" +
                "\t\t\t\t这是一封自动发送的邮件，请不要直接回复!\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</div>\t\n" +
                "\n" +
                "\t\t</div>\n" +
                "\t</body>\n" +
                "</html>";


        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(FROM);
            mimeMessageHelper.setTo(eMail);

            mimeMessageHelper.setSubject("兼职君 注册验证码");

            mimeMessageHelper.setText(message, true);


            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            return false;
        }

        return true;
    }
}
