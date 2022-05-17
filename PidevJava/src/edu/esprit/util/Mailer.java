/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.util;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Hrouz
 */
public class Mailer {
    
     public static void sendMail(String recipient,String code) throws Exception{
        System.out.println("Preparing to send email");
        Properties p = new Properties();
        
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        
        String myAccountEmail = "SouthSideLabLibro@gmail.com";
        String password = "libro123456789";
        
        Session session = Session.getInstance(p , new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(myAccountEmail, password);
      }
        });
        
        Message message = prepareMessage(session , myAccountEmail ,recipient,code );
        
        Transport.send(message);
        System.out.println("Message sent successfully");
    
    }
     
      private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String code) {
        try{
            Message message;
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Subject");
            message.setText(code);
            return message;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
}
