package com.xformics.todoserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    // generating the id value as auto
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // validating the user inputs
    @NotBlank(message = "Please provide some value as todo")
    @Length(min = 4, message = "Please provide at least 4 characters")
    private String title;

    private Boolean completed = false;
}
