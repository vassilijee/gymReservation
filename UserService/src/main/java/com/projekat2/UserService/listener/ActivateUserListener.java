package com.projekat2.UserService.listener;

import com.projekat2.UserService.dto.client.ActivateUserDto;
import com.projekat2.UserService.listener.helper.MessageHelper;
import com.projekat2.UserService.service.UserServis;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActivateUserListener {
    private MessageHelper messageHelper;
    private UserServis userServis;

    @JmsListener(destination = "${destination.activate.user}", concurrency = "5-10")
    public void activateUser(Message message) throws JMSException {
        ActivateUserDto activateUserDto = messageHelper.getMessage(message, ActivateUserDto.class);
        userServis.activateUser(activateUserDto);
    }

}
