package com.projekat2.NotificationService.service.serviceImpl;

import com.projekat2.NotificationService.domain.Email;
import com.projekat2.NotificationService.dto.emailDto.EmailActivationDto;
import com.projekat2.NotificationService.dto.emailDto.EmailDto;
import com.projekat2.NotificationService.dto.emailDto.EmailNewSessionDto;
import com.projekat2.NotificationService.mapper.EmailMapper;
import com.projekat2.NotificationService.repository.EmailRepository;
import com.projekat2.NotificationService.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmailServiceImplementation implements EmailService {

    private EmailRepository emailRepository;

    private EmailMapper emailMapper;

    public JavaMailSender mailSender;

    public EmailServiceImplementation(EmailRepository emailRepository, EmailMapper emailMapper, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.emailMapper = emailMapper;
        this.mailSender = mailSender;
    }

    @Override
    public List<EmailDto> findAllEmails(String authorization) {
        List<EmailDto> emailDtoList = new ArrayList<>();

        emailRepository.findAll().forEach(email -> {
            emailDtoList.add(emailMapper.emailToEmailDto(email));
        });


        return emailDtoList;
    }

    @Override
    public EmailDto addActivationEmailToEmail(EmailActivationDto emailActivationDto) {
        Email email = emailMapper.activationEmailDtoToEmail(emailActivationDto);

        emailRepository.save(email);
        return emailMapper.emailToEmailDto(email);
    }


    @Override
    public EmailDto addReservationEmailDtoToEmail(EmailNewSessionDto emailNewSessionDto) {
        Email email = emailMapper.reservationEmailDtoToEmail(emailNewSessionDto);
        emailRepository.save(email);
        return emailMapper.emailToEmailDto(email);
    }

//    @Override
//    public EmailDto addCancelReservationDtoToEmail(CancelReservationDto cancelReservationDto) {
//        Email email = emailMapper.cancelReservationDtoToEmail(cancelReservationDto);
//        emailRepository.save(email);
//        return emailMapper.emailToEmailDto(email);
//    }


    @Override
    public void sendSimpleMessage(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }


}
