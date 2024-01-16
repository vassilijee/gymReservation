package com.projekat2.SessionService.client.notifivationServis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String email;
    private List<String> params;
    private String type;
}

