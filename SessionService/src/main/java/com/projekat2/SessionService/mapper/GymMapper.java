package com.projekat2.SessionService.mapper;

import com.projekat2.SessionService.domain.Gym;
import com.projekat2.SessionService.dto.gym.GymCreateDto;
import com.projekat2.SessionService.dto.gym.GymDto;
import com.projekat2.SessionService.dto.gym.GymUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class GymMapper {
    public GymDto gymToGymDto(Gym gym) {
        return new GymDto(gym.getGymName(), gym.getGymDesc());
    }

    public Gym gymCreateDtoToGym(GymCreateDto gymCreateDto) {
        Gym gym = new Gym();
        gym.setGymName(gymCreateDto.getGymName());
        gym.setGymDesc(gymCreateDto.getGymDesc());
        gym.setPersonnelCount(gymCreateDto.getPersonnelCount());
        return gym;
    }

    public Gym gymUpdateDtoToGym (GymUpdateDto gymUpdateDto){
        return null;
    }
}
