package com.projekat2.NotificationService.service;


import com.projekat2.NotificationService.dto.emailDto.EmailActivationDto;
import com.projekat2.NotificationService.dto.emailDto.EmailDto;
import com.projekat2.NotificationService.dto.emailDto.EmailNewSessionDto;

import java.util.List;

public interface EmailService {

    List<EmailDto> findAllEmails(String authorization);

    EmailDto addActivationEmailToEmail(EmailActivationDto emailActivationDto);


    EmailDto addReservationEmailDtoToEmail(EmailNewSessionDto newSessionDto);

    //EmailDto addCancelReservationDtoToEmail(CancelReservationDto cancelReservationDto);

    //EmailDto addReviewDtoToEmail(ReviewDto reviewDto);

    void sendSimpleMessage(String to, String subject, String content);

}
