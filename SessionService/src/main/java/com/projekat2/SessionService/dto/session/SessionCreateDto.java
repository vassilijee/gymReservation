package com.projekat2.SessionService.dto.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class SessionCreateDto {
    @JsonProperty("gymid")
    private Long gymId;
    @JsonProperty("exercisetypeid")
    private Long exerciseTypeId;
    @Min(value = 0, message = "The value must be positive")
    private Integer currentCount;
}
