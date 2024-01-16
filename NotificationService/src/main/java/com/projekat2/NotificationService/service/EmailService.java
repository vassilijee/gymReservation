package com.projekat2.NotificationService.service;


import com.projekat2.NotificationService.dto.emailDto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, List<String> content);

}
