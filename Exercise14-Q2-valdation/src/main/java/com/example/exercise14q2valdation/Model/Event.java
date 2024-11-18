package com.example.exercise14q2valdation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "enter valid ID")
    @Size(min = 3, message = "enter id at least with three numbers")
    private String ID;
    @NotEmpty(message = "enter valid description")
    @Size(min = 15, message = "enter id at least with three numbers")
    private String description;
    @NotNull(message = "enter valid capacity")
    @Min(value = 25,message = "the minimum number is 25 ")
    @Digits(integer = 10, fraction = 0, message = "Capacity must be a whole number")
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;
}
