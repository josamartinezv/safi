package com.called.attention.service;


import com.called.attention.domain.Users;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class EmailService {

    public static final String LOGO_URL = "sena.png";

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailVerbalCalled(VerbalCalledDTO verbalCalledDTO) {

        Locale locale = Locale.forLanguageTag("es");
        Context context = new Context(locale);
        context.setVariable("imageResourceName", LOGO_URL);
        context.setVariable("name", verbalCalledDTO.getAprentice().getDetailUser().getName());
        context.setVariable("lastName", verbalCalledDTO.getAprentice().getDetailUser().getLastName());
        context.setVariable("documentNumber", verbalCalledDTO.getAprentice().getDocumentNumber());
        context.setVariable("phone", verbalCalledDTO.getAprentice().getDetailUser().getPhoneNumber());
        context.setVariable("email", verbalCalledDTO.getAprentice().getEmail());
        context.setVariable("instructor", verbalCalledDTO.getInstructor().getDetailUser().getName());
        context.setVariable("instructorlastName", verbalCalledDTO.getInstructor().getDetailUser().getLastName());

        String contentForm = templateEngine.process("mail/verbalcalled.html", context);
        sendEmail(verbalCalledDTO.getAprentice().getEmail(),
                verbalCalledDTO.getInstructor().getEmail(),
                "Llamado de atencion " + verbalCalledDTO.getAprentice().getDetailUser().getName(),
                contentForm, true, true);

    }

    public void sendEmailVerbalCalledUpdate(VerbalCalledDTO verbalCalledDTO) {

        Locale locale = Locale.forLanguageTag("es");
        Context context = new Context(locale);
        context.setVariable("imageResourceName", LOGO_URL);
        context.setVariable("name", verbalCalledDTO.getInstructor().getDetailUser().getName());
        context.setVariable("name", verbalCalledDTO.getAprentice().getDetailUser().getName());
        context.setVariable("lastName", verbalCalledDTO.getAprentice().getDetailUser().getLastName());

        String contentForm = templateEngine.process("mail/verbalCalledUpdate.html", context);
        sendEmail(verbalCalledDTO.getInstructor().getEmail(),
                verbalCalledDTO.getInstructor().getEmail(),
                "Llamado de atencion actualizado",
                contentForm, true, true);

    }
    public void sendEmailRegister(Users users) {

        Locale locale = Locale.forLanguageTag("es");
        Context context = new Context(locale);
        context.setVariable("imageResourceName", LOGO_URL);
        context.setVariable("name", users.getDetailUser().getName());
        context.setVariable("lastName", users.getDetailUser().getLastName());

        String contentForm = templateEngine.process("mail/register.html", context);
        sendEmail(users.getEmail(),
                users.getEmailSena(),
                "Registro",
                contentForm, true, true);

    }

    public void sendEmailAdminNotify(Users users) {

        Locale locale = Locale.forLanguageTag("es");
        Context context = new Context(locale);
        context.setVariable("imageResourceName", LOGO_URL);
        context.setVariable("name", users.getDetailUser().getName());
        context.setVariable("lastName", users.getDetailUser().getLastName());

        String contentForm = templateEngine.process("mail/adminNotification.html", context);
        sendEmail(users.getEmail(),
                users.getEmailSena(),
                "Usuario registrado",
                contentForm, true, true);

    }

    public void sendEmailConfirmation(Users users) {

        Locale locale = Locale.forLanguageTag("es");
        Context context = new Context(locale);
        context.setVariable("imageResourceName", LOGO_URL);
        context.setVariable("name", users.getDetailUser().getName());
        context.setVariable("lastName", users.getDetailUser().getLastName());

        String contentForm = templateEngine.process("mail/confirmation.html", context);
        sendEmail(users.getEmail(),
                users.getEmailSena(),
                "Rol asignado",
                contentForm, true, true);

    }




    private void sendEmail(String to, String cc, String subject, String content, boolean isHtml, boolean isMultipart) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setCc(cc);
            message.setSubject(subject);
            message.setText(content, isHtml);
            message.addInline(LOGO_URL, new ClassPathResource("/static/img/sena.png"));
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

