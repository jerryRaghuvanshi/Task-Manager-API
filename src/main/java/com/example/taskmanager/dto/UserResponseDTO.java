package com.example.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(

        description=
                "User Response"

)
public class UserResponseDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

}
