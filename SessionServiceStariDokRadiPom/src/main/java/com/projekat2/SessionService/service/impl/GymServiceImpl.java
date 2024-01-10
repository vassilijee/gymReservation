package com.projekat2.SessionService.service.impl;

import com.projekat2.SessionService.domain.Gym;
import com.projekat2.SessionService.dto.gym.GymCreateDto;
import com.projekat2.SessionService.dto.gym.GymDto;
import com.projekat2.SessionService.mapper.GymMapper;
import com.projekat2.SessionService.repository.GymRepository;
import com.projekat2.SessionService.service.GymService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GymServiceImpl implements GymService {
    //    private TokenService tokenService;
    private GymRepository gymRepository;
    private GymMapper gymMapper;

    public GymServiceImpl(GymRepository gymRepository, GymMapper gymMapper) {

        //this.tokenService = tokenService;
        this.gymRepository = gymRepository;
        this.gymMapper = gymMapper;
    }

    @Override
    public Page<GymDto> findAll(Pageable pageable) {
        return gymRepository.findAll(pageable).map(gymMapper::gymToGymDto);
    }

    @Override
    public GymDto findById(Long id) {
        return gymRepository.findById(id).map(gymMapper::gymToGymDto).get();
    }

    @Override
    public GymDto add(GymCreateDto gymCreateDto) {
        Gym gym = gymMapper.gymCreateDtoToGym(gymCreateDto);
        gymRepository.save(gym);
        return gymMapper.gymToGymDto(gym);
    }

    @Override
    public void deleteById(Long id) {
        gymRepository.deleteById(id);
    }
}
