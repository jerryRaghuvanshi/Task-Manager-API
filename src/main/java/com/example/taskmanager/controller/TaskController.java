package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.dto.TaskResponseDTO;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tasks")
@Tag(
        name = "Task Management",
        description = "CRUD operations for Task Manager API"
)
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper mapper;

    @Operation(
            summary = "Create Task",
            description = "Creates a new task in the system"
    )

    @ApiResponses({

            @ApiResponse(
                    responseCode = "201",
                    description = "Task created successfully"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            )

    })

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(

            @Valid
            @RequestBody TaskRequestDTO dto
    ) {

        Authentication authentication =


                SecurityContextHolder


                        .getContext()


                        .getAuthentication();
        String username =


                authentication.getName();


        Task task =
                mapper.toEntity(dto);


        Task savedTask =
                taskService.createTask(task,username);



        return ResponseEntity
                .status(HttpStatus.CREATED)

                .body(

                        mapper.toResponseDTO(

                                savedTask

                        )

                );


    }
    @Operation(

            summary="Get all tasks",

            description =
                    "Returns all tasks"

    )

    @ApiResponses({

            @ApiResponse(

                    responseCode="200",

                    description=
                            "Tasks retrieved"

            )

    })

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>>

    getAllTasks(){
        Authentication authentication =
                SecurityContextHolder.getContext()
                .getAuthentication();

        String userName = authentication.getName();


        List<Task> tasks =
                taskService.getAllTasks(userName);


        return ResponseEntity.ok(

                mapper.toResponseDTOs(

                        tasks

                )

        );


    }
    @Operation(

            summary =
                    "Get Task By Id"

    )

    @ApiResponses({

            @ApiResponse(

                    responseCode="200",

                    description=
                            "Task found"

            ),

            @ApiResponse(

                    responseCode="404",

                    description=
                            "Task not found"

            )

    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO>

    getTaskById(

            @PathVariable Long id
    ){




        Task task =
                taskService.getTaskById(id);





        return ResponseEntity.ok(

                mapper.toResponseDTO(

                        task

                )

        );


    }
    @Operation(

            summary=
                    "Delete Task"

    )

    @ApiResponses({

            @ApiResponse(

                    responseCode="204",

                    description=
                            "Task deleted"

            ),

            @ApiResponse(

                    responseCode="404",

                    description=
                            "Task not found"

            )

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id ) {


        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userName = authentication.getName();
        Task task = taskService.getTaskById(id);

        if(

                !task.getOwner()

                        .getUsername()

                        .equals(

                                userName

                        )

        ){

            throw new RuntimeException(

                    "Access denied"

            );

        }


        taskService.deleteTask(id);


        return ResponseEntity
                .noContent()
                .build();

    }
    @Operation(

            summary=
                    "Mark Task Completed"

    )

    @ApiResponses({

            @ApiResponse(

                    responseCode="200",

                    description=
                            "Task completed"

            ),

            @ApiResponse(

                    responseCode="404",

                    description=
                            "Task not found"

            )

    })
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponseDTO>

    completeTask(

            @PathVariable Long id
    ){

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userName = authentication.getName();
        Task task = taskService.getTaskById(id);

        if(

                !task.getOwner()

                        .getUsername()

                        .equals(

                                userName

                        )

        ){

            throw new RuntimeException(

                    "Access denied"

            );

        }



                taskService.markAsCompleted(id);



        return ResponseEntity.ok(

                mapper.toResponseDTO(

                        task

                )

        );


    }
    @Operation(

            summary=
                    "Update Task"

    )

    @ApiResponses({

            @ApiResponse(

                    responseCode="200",

                    description=
                            "Task updated"

            ),

            @ApiResponse(

                    responseCode="404",

                    description=
                            "Task not found"

            )

    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO>
    updateTask(

            @PathVariable Long id,

            @Valid @RequestBody TaskRequestDTO dto
    ) {


        Task task =

                mapper.toEntity(dto);
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userName = authentication.getName();

        if(

                !task.getOwner()

                        .getUsername()

                        .equals(

                                userName

                        )

        ){

            throw new RuntimeException(

                    "Access denied"

            );

        }



        Task updated =

                taskService.updateTask(

                        id,

                        task

                );



        return ResponseEntity.ok(

                mapper.toResponseDTO(

                        updated

                )

        );

    }
}
