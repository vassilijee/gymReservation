package com.projekat2.NotificationService.service;

import com.projekat2.NotificationService.dto.emailDto.MailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface MailService {
    Page<MailDto> findAll(Pageable pageable);
    Page<MailDto> findAllByDateRange(LocalDate beginDate, LocalDate endDate, Pageable pageable);
    Page<MailDto> findAllByType(String type, Pageable pageable);
    Page<MailDto> findAllByEmail(String email, Pageable pageable);
    Page<MailDto> findAllByDateRangeAndByEmail(LocalDate start,LocalDate end,String email, Pageable pageable);
    Page<MailDto> findAllByTypeAndByEmail(String type, String email, Pageable pageable);
    Page<MailDto> findAllByTypeAndByDateRange(String type, LocalDate beginDate, LocalDate endDate, Pageable pageable);
    Page<MailDto> findAllByTypeAndByEmailAndByDateRange(String type, String email, LocalDate beginDate, LocalDate endDate, Pageable pageable);

}
