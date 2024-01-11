package com.projekat2.NotificationService.mapper;

import com.projekat2.NotificationService.domain.Email;
import com.projekat2.NotificationService.dto.emailDto.EmailActivationDto;
import com.projekat2.NotificationService.dto.emailDto.EmailDto;
import com.projekat2.NotificationService.dto.emailDto.EmailNewSessionDto;
import com.projekat2.NotificationService.repository.NotificationTypeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmailMapper {

    private NotificationTypeRepository notificationTypeRepository;

    private String myMail = "sktestprj2@gmail.com";
    public EmailMapper(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }


    public EmailDto emailToEmailDto(Email email){
        EmailDto emailDto = new EmailDto();

        emailDto.setFrom(email.getFrom());
        emailDto.setTo(email.getTo());
        emailDto.setSubject(email.getSubject());
        emailDto.setContent(email.getContent());

        return emailDto;
    }

    public Email activationEmailDtoToEmail(EmailActivationDto emailActivationDto){
        Email email = new Email();

        String text = "Postovani " + emailActivationDto.getFirstName() + " " + emailActivationDto.getLastName() + " , verifikujte se na sledeci link:" + emailActivationDto.getLink();
        email.setContent(text);

        email.setFrom(myMail);
        email.setTo(emailActivationDto.getEmail());

        //VIDI STA CES SA OVIM
        email.setSubject(String.valueOf(notificationTypeRepository.findNotificationTypeByTypeName("ACTIVATION_MAIL").get()));
        email.setDate(String.valueOf(LocalDate.now()));


        return email;
    }

//    public Email changePasswordDtoToEmail(ChangePasswordDto changePasswordDto){
//        Email email = new Email();
//
//        email.setSubject(String.valueOf(notificationTypeRepository.findNotificationTypeByTypeName("CHANGE_PASSWORD_MAIL").get()));
//        email.setTo(changePasswordDto.getEmail());
//
//        String text = "Stara lozinka " + changePasswordDto.getOldPassword() + " je promenjena na "+ changePasswordDto.getNewPassword();
//        email.setContent(text);
//        //TODO
//        email.setFrom(myMail);
//        email.setDate(String.valueOf(LocalDate.now()));
//
//
//        return email;
//    }

    public Email reservationEmailDtoToEmail(EmailNewSessionDto emailNewSessionDto){
        Email email = new Email();

        email.setSubject(String.valueOf(notificationTypeRepository.findNotificationTypeByTypeName("RESERVATION_MAIL").get()));
        email.setTo(emailNewSessionDto.getEmail());
        //String text = "Uspesno ste rezervisali " + emailNewSessionDto.getCar() + "od " + emailNewSessionDto.getStartDate() + " u duzini od" + emailNewSessionDto.getDurationInDays();
        //email.setContent(text);
        email.setFrom(myMail);
        email.setDate(String.valueOf(LocalDate.now()));

        return email;
    }

//    public Email cancelReservationDtoToEmail(CancelReservationDto cancelReservationDto){
//        Email email = new Email();
//
//        email.setSubject(String.valueOf(notificationTypeRepository.findNotificationTypeByTypeName("CANCELATION_MAIL").get()));
//        email.setTo(cancelReservationDto.getEmail());
//        String text = "Rezervacija " + cancelReservationDto.getReservation() + " je otkazana";
//        email.setContent(text);
//        email.setFrom(myMail);
//        email.setDate(String.valueOf(LocalDate.now()));
//
//
//        return email;
//    }

//    public Email reviewDtoToEmail(ReviewDto reviewDto){
//        Email email = new Email();
//
//        email.setSubject(String.valueOf(notificationTypeRepository.findNotificationTypeByTypeName("REVIEW")));
//        email.setTo(reviewDto.getEmail());
//        String text = "Nova recenzija: \n" + "Ocena: " + reviewDto.getGrade() +"\nKomentar:" + reviewDto.getComment();
//        email.setContent(text);
//        email.setFrom(myMail);
//        email.setDate(String.valueOf(LocalDate.now()));
//        return email;
//    }



}
