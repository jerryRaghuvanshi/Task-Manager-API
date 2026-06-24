package com.example.taskmanager.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;

    @Size(max=100)
    private String description;
    private boolean completed=false;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(
            name="owner_id"
    )

    private User owner;


}
