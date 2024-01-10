package com.projekat2.SessionService.mapper;

import com.projekat2.SessionService.domain.Gym;
import com.projekat2.SessionService.dto.gym.GymCreateDto;
import com.projekat2.SessionService.dto.gym.GymDto;
import org.springframework.stereotype.Component;

@Component
public class GymMapper {
    public GymDto gymToGymDto(Gym gym) {
        GymDto gymDto = new GymDto();
        gymDto.setId(gym.getId());
        gymDto.setGymName(gym.getGymName());
        gymDto.setGymDesc(gym.getGymDesc());
        return gymDto;
    }

    public Gym gymCreateDtoToGym(GymCreateDto gymCreateDto) {
        Gym gym = new Gym();
        gym.setGymName(gymCreateDto.getGymName());
        gym.setGymDesc(gymCreateDto.getGymDesc());
        gym.setPersonnelCount(gymCreateDto.getPersonnelCount());
        return gym;
    }
}
