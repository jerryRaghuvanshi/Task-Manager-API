package com.example.taskmanager.dto;
import com.example.taskmanager.entity.User;
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
                "Request DTO for creating a task"
)
public class TaskRequestDTO{

    @Schema(

            description=
                    "Title of task",

            example=
                    "Learn Spring"

    )
    private String title;

    @Schema(

            description=
                    "Task description",

            example=
                    "Complete Swagger"

    )
    private String description;






}