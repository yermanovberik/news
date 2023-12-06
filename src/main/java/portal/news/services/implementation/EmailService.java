package portal.news.services.implementation;

import portal.news.configurations.MyAuthenticator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        String username = "210103114@stu.sdu.edu.kz";
        String password = "ifue xfdi hqdo qtlb";

        Session session = Session.getInstance(properties, new MyAuthenticator(username, password));

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("210103114@stu.sdu.edu.kz"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
    }
}

