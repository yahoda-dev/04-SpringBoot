package com.yahodadev.restapi.section03.valid;


import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/valid2/*")
public class ValidTestController {


    @PostMapping("/users")
    public ResponseEntity<Void> saveuser(
            @Valid @RequestBody UserDTO userDTO
    ) {
        System.out.println("userDTO = " + userDTO);

        return ResponseEntity.ok().build();
    }

//    @PostMapping("/users")
//    public ResponseEntity<?> saveuser(
//            @Valid @RequestBody UserDTO userDTO,
//            BindingResult bindingResult
//    ) {
//        if (bindingResult.hasErrors()) {
//            String errorMessage = bindingResult.getFieldErrors().stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .collect(Collectors.joining(", "));
//
//            return ResponseEntity.badRequest().body(errorMessage);
//        }
//
//        System.out.println("userDTO = " + userDTO);
//
//        return ResponseEntity.ok().build();
//    }
}
