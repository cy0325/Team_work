package org.nwnu.pub.util;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * -----------------------------------------
 * @文件: Email.java
 * @描述: 发送Email工具类
 * -----------------------------------------
 */
public class Email {

    private JavaMailSender javaMailSender;
    private SimpleMailMessage simpleMailMessage;

    /**
     * @方法名: sendMail
     * @参数名：@param subject  邮件主题
     * @参数名：@param content 邮件主题内容
     * @参数名：@param to         收件人Email地址
     * @描述语: 发送邮件
     */
    public boolean sendMail(String subject, String content, String to) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            /**
             * new MimeMessageHelper(mimeMessage,true)之true个人见解：
             * 关于true参数,官方文档是这样解释的：
             * use the true flag to indicate you need a multipart message
             * 翻译过来就是：使用true,以表明你需要多个消息
             * 再去翻一下MimeMessageHelper的API,找到这样一句话：
             * supporting alternative texts, inline elements and attachments
             * 也就是说,如果要支持内联元素(HTML)和附件就必须给定第二个参数为true
             * 否则抛出 java.lang.IllegalStateException 异常
             */
            /**
             * 刚开始收到邮件显示中文乱码,于是赶紧查看官方文档,没有提到,再查阅API,也没有查到Set编码的方法,
             * 于是就在HTML里面自己指定编码,也不起作用,邮件还是显示中文乱码;
             * 最后的结论是：如果HTML内容含有中文必须指定HTML的编码,默认是ISO-8859-1,所以会显示中文乱码
             * 在MimeMessageHelper的构造方法中找到：
             * MimeMessageHelper(MimeMessage mimeMessage, boolean multipart, String encoding)
             * 实验一下,OK,原来编码是在这儿设置,没事了,中文正常显示
             */
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom(simpleMailMessage.getFrom()); //设置发件人Email
            messageHelper.setSubject(subject); //设置邮件主题
            //messageHelper.setText(content);   //设置邮件主题内容
            messageHelper.setTo(to);          //设定收件人Email
            //步骤 1
            //资源的引用方法：cid:你自己设置的资源ID
            messageHelper.setText(content, true);
            /**
             * ClassPathResource：很明显就是类路径资源,我这里的附件是在项目里的,所以需要用ClassPathResource
             * 如果是系统文件资源就不能用ClassPathResource,而要用FileSystemResource,例：
             * FileSystemResource file = new FileSystemResource(new File("D:/woniu.png"));
             */
            /**
             * 如果想在HTML中使用资源,必须在HTML中通过资源 ID 先引用资源,然后才来加载资源
             */
            //步骤 2
//            ClassPathResource image = new ClassPathResource("/images/body.png");
//            messageHelper.addInline("imageid", image);
            javaMailSender.send(mimeMessage);    //发送HTML邮件

        return true;
        } catch (Exception e) {return false;}
    }
    //Spring 依赖注入
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }
    //Spring 依赖注入
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    //加密id
    public static String passId(String id, String name){
    	byte[] salt = PasswordUtil.getStaticSalt();
    	return PasswordUtil.encrypt(id, name, salt);
    }
}
