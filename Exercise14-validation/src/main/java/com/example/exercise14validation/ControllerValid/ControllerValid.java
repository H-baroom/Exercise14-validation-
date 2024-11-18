package com.example.exercise14validation.ControllerValid;

import com.example.exercise14validation.ApiResponse.ApiResponse;
import com.example.exercise14validation.Model.Model;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/project")
public class ControllerValid {
    private final View error;
    ArrayList<Model> models = new ArrayList();

    public ControllerValid(View error) {
        this.error = error;
    }

    @GetMapping("/get")
    public ArrayList<Model> getProject(){
        return models;
    }

    @PostMapping("/add")
    public ResponseEntity addModel(@RequestBody @Valid Model model, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        models.add(model);
        return ResponseEntity.status(200).body(new ApiResponse("Model added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity update(@PathVariable int index,@RequestBody @Valid Model model,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        models.set(index,model);
        return ResponseEntity.status(200).body(new ApiResponse("model updated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteModel(@PathVariable int index){
        models.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("model removed"));
    }

    @PutMapping("/changeStatus/{index}")
    public ResponseEntity changeStatus(@PathVariable int index) {
        if (!(models.get(index).getStatus().equalsIgnoreCase("Completed"))) {
            if (models.get(index).getStatus().equalsIgnoreCase("Not Started")){
                models.get(index).setStatus("in Progress");
                return ResponseEntity.status(200).body(new ApiResponse("in Progress"));
            } else if (models.get(index).getStatus().equalsIgnoreCase("in Progress")) {
                models.get(index).setStatus("Completed");
                return ResponseEntity.status(200).body(new ApiResponse("Project Completed"));
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("can not change status because Project Completed"));
    }

    @GetMapping("/searchProjectByTitle/{title}")
    public Model searchProjectByTitle(@PathVariable String title) {
        for (Model model : models) {
            if (model.getTitle().equals(title)) {
                return model;
            }
        }
        return null;
    }

    @GetMapping("/displayAllProjectsByCompanyName/{companyName}")
    public ArrayList<Model> displayAllProjectsByCompanyName(@PathVariable String companyName) {
        ArrayList<Model> projectsCompanyName = new ArrayList<>();
        for (Model model : models) {
            if (model.getCompanyName().equals(companyName)) {
                projectsCompanyName.add(model);
            }
        }
        return projectsCompanyName;
    }
}
