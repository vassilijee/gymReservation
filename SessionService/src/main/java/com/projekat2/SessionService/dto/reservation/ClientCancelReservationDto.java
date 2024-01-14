package com.projekat2.SessionService.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientCancelReservationDto {
    private Long clientId;
    private Long sessionId;
}
