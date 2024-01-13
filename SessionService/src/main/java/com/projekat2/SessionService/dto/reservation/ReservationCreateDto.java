package com.projekat2.SessionService.dto.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReservationCreateDto {
    @JsonProperty("sessionid")
    private Long sessionId;
    private Long clientId;

}
