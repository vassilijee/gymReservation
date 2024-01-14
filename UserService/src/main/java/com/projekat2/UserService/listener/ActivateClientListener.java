package com.projekat2.UserService.listener;

import com.projekat2.UserService.dto.client.ActivateClientDto;
import com.projekat2.UserService.listener.helper.MessageHelper;
import com.projekat2.UserService.service.UserServis;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActivateClientListener {
    private MessageHelper messageHelper;
    private UserServis userServis;

    @JmsListener(destination = "${destination.activate.client}", concurrency = "5-10")
    public void activateClient(Message message) throws JMSException {
        ActivateClientDto activateClientDto = messageHelper.getMessage(message, ActivateClientDto.class);
        userServis.activateClient(activateClientDto);
    }

}
