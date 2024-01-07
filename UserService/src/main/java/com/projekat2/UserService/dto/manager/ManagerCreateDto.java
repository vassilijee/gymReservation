package com.projekat2.UserService.dto.manager;

import com.projekat2.UserService.dto.UserCreateDto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class ManagerCreateDto extends UserCreateDto {

    @NotBlank
    private String gymName;
    private LocalDate startDate;
}
