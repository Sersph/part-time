package com.tidc.part.time.monarch.util;


import com.tidc.part.time.monarch.exception.ResultException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EMailUtil {

    @Value("${email.util.email}")
    private static String FROM = "2675835744@qq.com";

    public static boolean informEnterprise(String email, String partTimeName, JavaMailSender javaMailSender){
        System.err.println("EMailUtil informEnterprise" + email);

        String message = "<html>\n" +
                "<body style=\"background: #555; position: relative;\">\n" +
                "    <div style=\"margin: 0 auto; width: 700px; background: #bbd8e6; padding:10px 50px; box-sizing: border-box;\">\n" +
                "        <div style=\"width: 200px;height: 100px;\">\n" +
                "            <h1 style=\"color: #0AF; font-family: Algerian; font-size: 50px;\">J兼职君</h1>\n" +
                "        </div>\n" +
                "        <div style=\"font-size: 18px;\">\n" +
                "            <h2 style=\"color: #0AF;\">亲爱的兼职君用户，您好:</h2>\n" +
                "            <p style=\"text-indent: 2em; line-height: 30px;\">\n" +
                "                您在兼职君发布兼职“ " + partTimeName + " ”有了新的动态\n" +
                "            </p>\n" +
                "                \n" +
                "            <p style=\"line-height: 30px;\">\n" +
                "                祝在【兼职君】收获人才！\n" +
                "            </p>\n" +
                "            <p style=\"line-height: 30px;\">\n" +
                "                这是一封自动发送的邮件，请不要直接回复!\n" +
                "            </p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        return sendEmail(email, "兼职君 新的动态", message, javaMailSender);
    }

    public static boolean sendMailCaptcha(String email, String code, JavaMailSender javaMailSender){

        String message = "<html>\n" +
                "\t<body style=\"background: #555;\">\n" +
                "\t\t<div style=\"margin: 0 auto; width: 700px; background: #bbd8e6; padding:10px 50px; box-sizing: border-box;\">\n" +
                "\t\t\t\n" +
                "\t\t\t<div style=\"width: 200px;height: 100px;\">\n" +
                "\t\t\t\t<h1 style=\"color: #0AF; font-family: Algerian; font-size: 50px;\">J兼职君</h1>\n" +
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

        return sendEmail(email, "兼职君 注册验证码", message, javaMailSender);
    }

    private static boolean sendEmail(String email, String subject, String message, JavaMailSender javaMailSender){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(FROM);
            mimeMessageHelper.setTo(email);

            mimeMessageHelper.setSubject(subject);

            mimeMessageHelper.setText(message, true);


            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            e.printStackTrace();
            throw new ResultException(0, "未知错误");
        }
        return true;
    }
}
