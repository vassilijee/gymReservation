package com.projekat2.NotificationService.service.serviceImpl;

import com.projekat2.NotificationService.domain.Mail;
import com.projekat2.NotificationService.domain.NotificationType;
import com.projekat2.NotificationService.mapper.MailMapper;
import com.projekat2.NotificationService.repository.MailRepository;
import com.projekat2.NotificationService.repository.NotificationTypeRepository;
import com.projekat2.NotificationService.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmailServiceImplementation implements EmailService {

    private MailRepository mailRepository;

    private MailMapper mailMapper;
    private NotificationTypeRepository notificationTypeRepository;
    @Autowired
    public JavaMailSender mailSender;

    public EmailServiceImplementation(MailRepository mailRepository, MailMapper mailMapper, JavaMailSender mailSender, NotificationTypeRepository notificationTypeRepository) {
        this.mailRepository = mailRepository;
        this.mailMapper = mailMapper;
        this.mailSender = mailSender;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, List<String> content) {
        SimpleMailMessage message = new SimpleMailMessage();
        NotificationType notificationType = notificationTypeRepository.findNotificationTypeByTypeName(subject).get();
        String text = notificationType.getText();
        for (int i = 0; i < content.size(); i++) {
            //notificationType.setText(notificationType.getText().replace("string"+(i+1), content.get(i)));
            text = text.replace("string" + (i + 1), content.get(i));
        }
        Mail mail = new Mail(to, notificationType, content);
        mailRepository.save(mail);
        message.setTo(to);
        message.setSubject(notificationType.getTypeName());
        message.setText(text);
        mailSender.send(message);
    }


}
