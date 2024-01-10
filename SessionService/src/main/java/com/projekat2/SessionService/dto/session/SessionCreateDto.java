package com.projekat2.SessionService.dto.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SessionCreateDto {
    @JsonProperty("gymid")
    private Long gymId;
    @JsonProperty("exercisetypeid")
    private Long exerciseTypeId;
    @NotBlank
    private Date date;
    @NotBlank
    private Time time;
}
