package com.example.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapping;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(

        description=
                "Task Response"

)

public class TaskResponseDTO{


    @Schema(

            example="1"
    )
    private Long id;



    @Schema(

            example="true"
    )
    private boolean completed;

    private String title;

    private String description;


    private String ownerUsername;









}