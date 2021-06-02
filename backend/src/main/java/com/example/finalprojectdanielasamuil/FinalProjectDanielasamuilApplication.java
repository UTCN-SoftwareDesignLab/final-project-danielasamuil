package com.example.finalprojectdanielasamuil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class FinalProjectDanielasamuilApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectDanielasamuilApplication.class, args);

    }

}

/*
@SpringBootApplication
public class FinalProjectDanielasamuilApplication implements CommandLineRunner {

    @Autowired
    private JavaMailSender javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectDanielasamuilApplication.class, args);

    }

    @Override
    public void run(String... args) {

        System.out.println("Sending Email...");

        sendEmail();

        System.out.println("Done");

    }
 */
 /*   void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("test.email.danielasamuil@gmail.com");

        msg.setSubject("New subscription to our gym");
        msg.setText("Hello \n This is your confirmation email for your new gym class subscription \n Have a nice day");

        javaMailSender.send(msg);

    }

}
 */