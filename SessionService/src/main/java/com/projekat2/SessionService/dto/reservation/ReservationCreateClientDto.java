package com.projekat2.SessionService.dto.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationCreateClientDto {
    String date;
    String time;
    String exerciseTypeName;
}
