package com.backendapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendapi.api.dtos.reqdto.LogRequestDto;
import com.backendapi.api.dtos.reqdto.RegisterRequestDTO;
import com.backendapi.api.dtos.respdto.UserResponse;
import com.backendapi.api.dtos.respdto.WrapLoginResultAndDto;
import com.backendapi.api.model.enums.LoginResult;
import com.backendapi.api.model.enums.RegisterResult;
import com.backendapi.api.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<String> registered(@Valid @RequestBody RegisterRequestDTO newUser) {
        RegisterResult r = userService.createUser(newUser);
        if (r == RegisterResult.DUPLICATE_USER) {
            return new ResponseEntity<>("Already Username/gmail ragistered", HttpStatus.CONFLICT);
        } else if (r == RegisterResult.ERROR) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("user created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<?> logging(@Valid @RequestBody LogRequestDto logRequestDto) {
        WrapLoginResultAndDto lr = userService.login(logRequestDto);
        if (lr.getLoginResult() == LoginResult.FAIL) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (lr.getLoginResult() == LoginResult.NOT_FOUND) {
            return new ResponseEntity<>("Please Register First", HttpStatus.BAD_REQUEST);
        } else if (lr.getLoginResult() == LoginResult.WRONG_PASSWORD) {
            return new ResponseEntity<>("please Provide correct password", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(lr.getLoginResponseDto(), HttpStatus.ACCEPTED);
    } 
    
    @GetMapping("/user")
    ResponseEntity<List<UserResponse>> getAllUser(){
        return new ResponseEntity<>(userService.fetchAllUser(),HttpStatus.OK) ;
    }

    @GetMapping("/user/{id}")
    ResponseEntity<UserResponse> getUserId(@PathVariable  Long id){
        return userService.fetchUser(id)
        .map(ResponseEntity:: ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping ("/user/update/{id}")
    ResponseEntity<String> updateUser(@PathVariable Long id,@Valid @RequestBody RegisterRequestDTO updateUserDto){
        if(userService.updateUser(id, updateUserDto)) 
            return new ResponseEntity<>("User updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
