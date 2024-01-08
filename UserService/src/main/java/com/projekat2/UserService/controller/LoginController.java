package com.projekat2.UserService.controller;

import com.projekat2.UserService.dto.token.TokenRequestDto;
import com.projekat2.UserService.dto.token.TokenResponseDto;
import com.projekat2.UserService.service.UserServis;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/login")
public class LoginController {
    private UserServis userServis;

    public LoginController(UserServis userServis) {
        this.userServis = userServis;
    }

    @ApiOperation(value = "Login")
    @PostMapping("")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userServis.login(tokenRequestDto), HttpStatus.OK);
    }
}
