package com.projekat2.NotificationService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "mail")
@EntityListeners(AuditingEntityListener.class)
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;
    @ManyToOne
    private NotificationType type;
    private List<String> parameters = new ArrayList<>();
    @Column
    @CreatedDate
    private LocalDate createdAt;

    public Mail(String email, NotificationType type, List<String> parameters) {
        this.email = email;
        this.type = type;
        this.parameters = parameters;
    }
}
