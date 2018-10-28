package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import models.Item;

public class SendEmail {
    
    public static Queue<Item> items = new ConcurrentLinkedQueue<Item>();

    public static void process() {
        if (items.isEmpty()) {
            System.out.println("no item, don't send email");
            return;
        }

        final String username = "findscgroup@gmail.com";
        final String password = "shunkaizzz101";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("findscgroup@gmail.com"));
            Set<String> userEmails = Utils.getAllEmails();
            String allEmailsString = "";
            int j = 0;
            for (String email : userEmails) {
                allEmailsString += email;
                if (j != (userEmails.size() - 1)) {
                    allEmailsString += ",";
                }
                j++;
            }
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(allEmailsString));
            message.setSubject("FindSC Update");
            String timeStamp = new SimpleDateFormat("HH:mm").format(new Date());
            String emailContent = "";
            emailContent += "Dear Trojan," + "\n\n Your email preferences are currently set to a 30 seconds Auto Digest, so you'll receive at most one email every 30 seconds.\n\n";
            for (int i = 0; i < items.size(); i++) {
                Item item = items.poll();
                emailContent += "New Post: Lost " + item.getName() + "\n\n";
                emailContent += "Posted by " + item.getAuthor().getUsername() + "\n\n";
                emailContent += "Description: " + item.getDescription() + "\n\n";
            }

            emailContent += "\n\n FindSC Activities Digest Since " + timeStamp;
            emailContent += "\n\n Thanks, \n\n FindSC Group";
            message.setText(emailContent);

            Transport.send(message);
        } catch (MessagingException e) {
            System.out.print("messagingexception");
        }
    }

}
