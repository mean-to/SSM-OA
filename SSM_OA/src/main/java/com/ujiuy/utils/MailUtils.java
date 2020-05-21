package com.ujiuy.utils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class MailUtils {
	private static String smtp_host = "smtp.126.com";
	private static String username = "zhaojing1144@126.com";
	private static String password = "qaz123";

	/*
	 * 参数一：收件人的邮箱地址
	 * 参数二：邮件标题
	 * 参数三：邮件内容
	 */
	//发送简单邮件
	public static void sendSimpleMail(String to, String subject, String msg) {
		Properties props = new Properties();
		props.setProperty("mail.host", smtp_host);
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");
		// 创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                // 密码验证
                return new PasswordAuthentication("zhaojing1144", "qaz1234");// 邮箱的授权码
            }
        };
		
		try {
			// 1.创建会话
			Session session = Session.getInstance(props,auth);
			// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
			session.setDebug(true);
			// 2.获取传输对象
			Transport transport = session.getTransport();
			// 3.设置连接
			transport.connect("smtp.126.com", username, password);
			// 4.创建邮件
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));//发送人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//接收人
			message.setSubject(subject);//标题
			
			message.setContent(msg, "text/html;charset=utf-8");//内容
			// 5.发送邮件
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("邮件发送失败");
		}
	}

	//发送带有附件的邮件
	public static void sendAttachmentMail(String to, String subject, String msg, File file) {
		Properties props = new Properties();
		props.setProperty("mail.host", smtp_host);
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");
		
		// 创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                // 密码验证
                return new PasswordAuthentication("zhaojing1144", "qaz1234");// 邮箱的授权码
            }
        };
		
		try {
			// 1.创建会话
			Session session = Session.getInstance(props,auth);
			// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
			session.setDebug(true);
			// 2.获取传输对象
			Transport transport = session.getTransport();
			// 3.设置连接
			transport.connect("smtp.126.com", username, password);
			// 4.创建邮件
			// 邮件头
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(username));
			message.setSubject(subject);
			
			// 邮件体（发送的字符串内容+附件）
			Multipart multipart = new MimeMultipart();
			// 信息（发送的字符串内容）
			BodyPart content = new MimeBodyPart();
			content.setContent(msg, "text/html;charset=utf-8");
			// 添加信息
			multipart.addBodyPart(content);
			
			// 附件
			BodyPart attachment = new MimeBodyPart();
			//附件对象----->路径------>附件转换为DataSource源------>DataSource源封装到附件对象中
			String filePath = file.getPath();
			FileDataSource fileDataSource = new FileDataSource(filePath);
			attachment.setDataHandler(new DataHandler(fileDataSource));
			
			//设置接收到 的附件名
			String filename = fileDataSource.getName();
			attachment.setFileName(MimeUtility.encodeText(filename));
			
			// 添加附件
			multipart.addBodyPart(attachment);
			// 5.发送邮件
			message.setContent(multipart);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("邮件发送失败");
		}
	}

	/*public static void main(String[] args) {
		//sendSimpleMail("1596994348@qq.com", "测试邮件", "Hello World");
		String path = "e:\\arcs\\arc.doc";
		File file = new File(path);
		sendAttachmentMail("2572482562@qq.com", "测试邮件", "oa文档", file);
	}*/

}
