package com.projekat2.SessionService.dto.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionCancelDto {
    private Long id;
    private boolean free;

}
