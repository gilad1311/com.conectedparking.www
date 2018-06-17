package com.connectedparking.www;

import com.sun.mail.dsn.Report;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;




public class Mail {
    private static WebElement element = null;
    private static WebDriver driver = null;
    public static int result = 1;
    //set driver
    public Mail(WebDriver driver) {
        this.driver = driver;
    }
    public static void send_Report(String test) {


        // Create object of Property file
        Properties props = new Properties();

        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.gmail.com");

        // set the port of socket factory
        props.put("mail.smtp.socketFactory.port", "465");

        // set socket factory
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        // set the authentication to true
        props.put("mail.smtp.auth", "true");

        // set the port of SMTP server
        props.put("mail.smtp.port", "465");

        // This will handle the complete authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("connectedcarsteam3", "Cars1234");
            }
        });

        try {

            // Create object of SimpleDateFormat class and decide the format
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");

            //get current date time with Date()
            Date date = new Date();

            // Now format the date
            String date1= dateFormat.format(date);

            // Create object of MimeMessage class
            Message message = new MimeMessage(session);

            // Set the from address
            message.setFrom(new InternetAddress("connectedcarsteam3@gmail.com"));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("gilad.robin@sap.com"));
//            message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("inbal.motena@sap.com"));

            // Add the subject link
            message.setSubject("Automation Report for "+ date1);

            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();

            // Set the body of email
            messageBodyPart1.setText("Hi! " +
                    "   Pleas fined the attached report for the "+test+" test.The automated test got performed on "+date1);

            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            // Mention the file which you want to send
            String filename = "//Iltlvp01//Public//Automation//Reports//"+test+".html";

            // Create data source and pass the filename
            DataSource source = new FileDataSource(filename);

            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));

            // set the file
            messageBodyPart2.setFileName(filename);

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            // add body part 1
            multipart.addBodyPart(messageBodyPart2);

            // add body part 2
            multipart.addBodyPart(messageBodyPart1);

            // set the content
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);

            System.out.println("=====Email Sent=====");

        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }

    }

}