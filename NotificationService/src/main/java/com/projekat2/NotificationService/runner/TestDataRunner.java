package com.projekat2.NotificationService.runner;


import com.projekat2.NotificationService.domain.NotificationType;
import com.projekat2.NotificationService.repository.NotificationTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Transactional
@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {
    private NotificationTypeRepository notificationTypeRepository;


    public TestDataRunner(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        NotificationType notificationType = new NotificationType();
        notificationType.setTypeName("Activation");
        notificationType.setText("Welcome string1 string2, here is your activation link : string3");

        NotificationType notificationType2 = new NotificationType();
        notificationType2.setTypeName("PasswordChange");
        notificationType2.setText("Hello string1 string2, you have successfully changed your password.");

        NotificationType notificationType3 = new NotificationType();
        notificationType3.setTypeName("Cancellation");
        notificationType3.setText("Hello string1 string2, your session on string3 , at this time: string4 has been canceled.");


        NotificationType notificationType4 = new NotificationType();
        notificationType4.setTypeName("Reservation");
        notificationType4.setText("Hello string1 string2, you have successfully reserve a session on string3 , at this time: string4. ");

        NotificationType notificationType5 = new NotificationType();
        notificationType5.setTypeName("Reminder");
        notificationType5.setText("Hello string1 string2, this is a reminder that you have a training tomorrow , at this time: string3. ");

        notificationTypeRepository.save(notificationType);
        notificationTypeRepository.save(notificationType2);
        notificationTypeRepository.save(notificationType3);
        notificationTypeRepository.save(notificationType4);
        notificationTypeRepository.save(notificationType5);
    }
}
