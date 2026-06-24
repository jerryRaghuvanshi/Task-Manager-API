package com.example.taskmanager.controller;

import com.example.taskmanager.dto.LoginRequestDTO;
import com.example.taskmanager.dto.LoginResponseDTO;
import com.example.taskmanager.dto.UserRequestDTO;
import com.example.taskmanager.dto.UserResponseDTO;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.mapper.UserMapper;
import com.example.taskmanager.security.JwtService;
import com.example.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(
        name="Authentication",
        description="Authentication APIs"
)
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;


    private final UserService userService;

    private final UserMapper mapper;


    @Operation(

            summary = "Register User",

            description =
                    "Registers a new user"

    )
    @ApiResponses({

            @ApiResponse(

                    responseCode = "201",

                    description =
                            "User registered"

            ),

            @ApiResponse(

                    responseCode = "400",

                    description =
                            "Bad Request"

            )

    })
    @PostMapping("/register")

    public ResponseEntity<UserResponseDTO>

    register(

            @Valid

            @RequestBody

            UserRequestDTO dto
    ) {


        User user =

                mapper.toEntity(dto);




        User savedUser =

                userService.create(user);


        return ResponseEntity

                .status(HttpStatus.CREATED)

                .body(

                        mapper.toResponseDTO(

                                savedUser

                        )

                );


    }

    @PostMapping("/login")


    public ResponseEntity<LoginResponseDTO>

    login(

            @RequestBody LoginRequestDTO dto
    ){



        Authentication authentication =


                authenticationManager.authenticate(


                        new UsernamePasswordAuthenticationToken(


                                dto.getUsername(),

                                dto.getPassword()


                        )

                );



        String token =

                jwtService.generateToken(

                        (UserDetails) authentication.getPrincipal()

                );






        return ResponseEntity.ok(

                new LoginResponseDTO(

                        token

                )

        );



    }
}
