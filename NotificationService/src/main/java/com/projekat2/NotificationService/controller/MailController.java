package com.projekat2.NotificationService.controller;

import com.projekat2.NotificationService.dto.emailDto.MailDto;
import com.projekat2.NotificationService.secutiry.CheckSecurity;
import com.projekat2.NotificationService.secutiry.service.TokenService;
import com.projekat2.NotificationService.service.EmailService;
import com.projekat2.NotificationService.service.MailService;
import io.jsonwebtoken.Claims;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/email")
public class MailController {
    private MailService mailService;
    private EmailService emailService;
    private TokenService tokenService;
    public MailController(MailService mailService, TokenService tokenService, EmailService emailService){
        this.mailService=mailService;
        this.tokenService=tokenService;
        this.emailService=emailService;
    }

    @GetMapping
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<Page<MailDto>> getAllMail(@RequestHeader("authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(mailService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/user")
    @CheckSecurity(roles = {"Manager", "Client"})
    public ResponseEntity<Page<MailDto>> getAllMailForUser(@RequestHeader("authorization") String authorization, Pageable pageable) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        return new ResponseEntity<>(mailService.findAllByEmail(claims.get("email").toString(),pageable), HttpStatus.OK);
    }
    @GetMapping("/type/{string}")
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<Page<MailDto>> getType(@RequestHeader("authorization") String authorization, @PathVariable String string, Pageable pageable) {
        return new ResponseEntity<>(mailService.findAllByType(string, pageable), HttpStatus.OK);
    }
    @GetMapping("/type/{string}/{string1}")
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<Page<MailDto>> getType(@RequestHeader("authorization") String authorization, @PathVariable String string, @PathVariable String string1,Pageable pageable) {
        return new ResponseEntity<>(mailService.findAllByTypeAndByEmail(string, string1,pageable), HttpStatus.OK);
    }
    @GetMapping("/user/type/{string}")
    @CheckSecurity(roles = {"Manager", "Client"})
    public ResponseEntity<Page<MailDto>> getTypeForUser(@RequestHeader("authorization") String authorization, @PathVariable String string, Pageable pageable) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        return new ResponseEntity<>(mailService.findAllByTypeAndByEmail(string, claims.get("email").toString(), pageable), HttpStatus.OK);
    }
    @GetMapping("/range/{string}")
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<Page<MailDto>> getDateRange(@RequestHeader("authorization") String authorization, @PathVariable String string, Pageable pageable) {
        List<String> string1 = List.of(string.split("-"));
        return new ResponseEntity<>(mailService.findAllByDateRange(LocalDate.parse(string1.get(0)), LocalDate.parse(string1.get(1)),pageable), HttpStatus.OK);
    }
    @GetMapping("/user/range/{string}")
    @CheckSecurity(roles = {"Manager", "Client"})
    public ResponseEntity<Page<MailDto>> getDateRangeForUser(@RequestHeader("authorization") String authorization, @PathVariable String string, Pageable pageable) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        List<String> string1 = List.of(string.split("-"));
        return new ResponseEntity<>(mailService.findAllByDateRangeAndByEmail(LocalDate.parse(string1.get(0)), LocalDate.parse(string1.get(1)),claims.get("email").toString(), pageable), HttpStatus.OK);
    }

    @GetMapping("/typeandrange/{string}/{string1}")
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<Page<MailDto>> getDateRangeAndType(@RequestHeader("authorization") String authorization, @PathVariable String string, @PathVariable String string1,Pageable pageable) {
        List<String> string2 = List.of(string1.split("-"));
        return new ResponseEntity<>(mailService.findAllByTypeAndByDateRange(string,LocalDate.parse(string2.get(0)), LocalDate.parse(string2.get(1)),pageable), HttpStatus.OK);
    }
    @GetMapping("/user/typeanderange/{string}/{string1}")
    @CheckSecurity(roles = {"Manager", "Client"})
    public ResponseEntity<Page<MailDto>> getDateRangeAndTypeForUser(@RequestHeader("authorization") String authorization, @PathVariable String string, @PathVariable String string1,Pageable pageable) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        List<String> string2 = List.of(string1.split("-"));
        return new ResponseEntity<>(mailService.findAllByTypeAndByEmailAndByDateRange(string,claims.get("email").toString(),LocalDate.parse(string2.get(0)), LocalDate.parse(string2.get(1)), pageable), HttpStatus.OK);
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendEmail()
    {
        emailService.sendSimpleMessage("llazarevic6021rn@raf.rs", "Reminder", List.of("Lana","Lazarevic","19:00"));
        return new ResponseEntity<>("Sent.", HttpStatus.OK);
    }
}
