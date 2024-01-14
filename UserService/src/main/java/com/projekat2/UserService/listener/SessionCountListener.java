package com.projekat2.UserService.listener;

import com.projekat2.UserService.dto.client.ChangeSessionCountDto;
import com.projekat2.UserService.listener.helper.MessageHelper;
import com.projekat2.UserService.service.UserServis;
import jakarta.jms.Message;
import jakarta.jms.JMSException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SessionCountListener {
    private MessageHelper messageHelper;
    private UserServis userServis;

    public SessionCountListener(MessageHelper messageHelper, UserServis userServis) {
        this.messageHelper = messageHelper;
        this.userServis = userServis;
    }

    @JmsListener(destination = "${destination.increment.session.count}", concurrency = "5-10")
    public void incrementSessionCount(Message message) throws JMSException {
        ChangeSessionCountDto changeSessionCountDto = messageHelper.getMessage(message, ChangeSessionCountDto.class);
        userServis.incrementClintSessionCount(changeSessionCountDto);
    }

    @JmsListener(destination = "${destination.decrement.session.count}", concurrency = "5-10")
    public void decrementSessionCount(Message message) throws JMSException {
        ChangeSessionCountDto changeSessionCountDto = messageHelper.getMessage(message, ChangeSessionCountDto.class);
        userServis.decrementClintSessionCount(changeSessionCountDto);
    }
}

