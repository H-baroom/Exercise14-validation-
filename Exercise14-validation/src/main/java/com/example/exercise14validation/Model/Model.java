package com.example.exercise14validation.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Model {
    @NotEmpty(message = "Enter valid ID")
    @Size(min = 3,max = 30,message = "Enter ID at least with 3 numbers")
    private String ID;
    @NotEmpty(message = "Enter valid title")
    @Size(min = 9,message = "Enter title at least with 9 character")
    private String title;
    @NotEmpty(message = "Enter valid Description")
    @Size(min = 15,max = 150,message = "Enter title at least with 15 character")
    private String description;
    @NotEmpty(message = "Enter valid status")
    @Pattern(regexp = "Not Started|in Progress|Completed",  message = "Status must be 'Not Started', 'in Progress', or 'Completed'")
    private String status;
    @NotEmpty(message = "Enter valid Company Name")
    @Size(min = 7,max = 25,message = "Enter Company Name at least with 7 character")
    private String companyName;
}
