package com.projekat2.SessionService.dto.reservation;

import com.projekat2.SessionService.dto.session.SessionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReservationDto {
    private SessionDto sessionDto;
    private Long clientId;
}
