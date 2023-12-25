package com.projekat2.SessionService.controller;

import com.projekat2.SessionService.dto.gym.GymDto;
import com.projekat2.SessionService.service.GymService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gym")
public class GymController {
    private GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @ApiOperation(value = "Get all gyms")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    public ResponseEntity<Page<GymDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                    Pageable pageable) {

        return new ResponseEntity<>(gymService.findAll(pageable), HttpStatus.OK);
    }
}
