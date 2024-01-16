package com.projekat2.NotificationService.service.serviceImpl;

import com.projekat2.NotificationService.dto.emailDto.MailDto;
import com.projekat2.NotificationService.mapper.MailMapper;
import com.projekat2.NotificationService.repository.MailRepository;
import com.projekat2.NotificationService.service.MailService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
@Transactional
public class MailServiceImplementation implements MailService {
    private MailRepository mailRepository;
    private MailMapper mailMapper;

    public MailServiceImplementation(MailRepository mailRepository, MailMapper mailMapper) {
        this.mailRepository = mailRepository;
        this.mailMapper = mailMapper;
    }

    @Override
    public Page<MailDto> findAll(Pageable pageable) {
        return mailRepository.findAll(pageable).map(mailMapper::mailToMailDto);
    }

    @Override
    public Page<MailDto> findAllByDateRange(LocalDate beginDate, LocalDate endDate, Pageable pageable) {
        return mailRepository.findByDateRange(beginDate,endDate,pageable).map(mailMapper::mailToMailDto);
    }

    @Override
    public Page<MailDto> findAllByType(String type, Pageable pageable) {
        return mailRepository.findByType(type, pageable).map(mailMapper::mailToMailDto);
    }

    @Override
    public Page<MailDto> findAllByEmail(String email, Pageable pageable) {
        return mailRepository.findByEmail(email,pageable).map(mailMapper::mailToMailDto);
    }

    @Override
    public Page<MailDto> findAllByDateRangeAndByEmail(LocalDate start, LocalDate end, String email, Pageable pageable) {
        return mailRepository.findByDateRangeAndByEmail(start,end,email,pageable).map(mailMapper::mailToMailDto);
    }

    @Override
    public Page<MailDto> findAllByTypeAndByEmail(String type, String email, Pageable pageable) {
        return mailRepository.findByTypeAndByEmail(type,email,pageable).map(mailMapper::mailToMailDto);
    }

    @Override
    public Page<MailDto> findAllByTypeAndByDateRange(String type, LocalDate beginDate, LocalDate endDate, Pageable pageable) {
        return mailRepository.findByTypeAndByDateRange(beginDate,endDate,type,pageable).map(mailMapper::mailToMailDto);
    }

    @Override
    public Page<MailDto> findAllByTypeAndByEmailAndByDateRange(String type, String email, LocalDate beginDate, LocalDate endDate, Pageable pageable) {
        return mailRepository.findByTypeAndByEmailAndByDateRange(type,email,beginDate,endDate,pageable).map(mailMapper::mailToMailDto);
    }
}
