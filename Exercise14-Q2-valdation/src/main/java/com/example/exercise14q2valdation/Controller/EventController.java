package com.example.exercise14q2valdation.Controller;

import com.example.exercise14q2valdation.ApiResponse.ApiResponse;
import com.example.exercise14q2valdation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/event")
public class EventController {
    ArrayList<Event> events = new ArrayList();

    @GetMapping("/get")
    public ArrayList get(){
        return events;
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors error){
        if (error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return  ResponseEntity.status(200).body(new ApiResponse("Event added"));
    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@RequestBody @Valid Event event, @PathVariable int index,Errors error) {
        if (error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted"));
    }

    @PutMapping("/changeCapacity/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index ,@PathVariable int capacity) {
        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(200).body(new ApiResponse("Event capacity changed"));
    }

    @GetMapping("/searchEventByGivenID/{id}")
    public Event searchEventByGivenID(@PathVariable String id){
        for (Event event : events) {
            if (event.getID().equals(id)) {
                return event;
            }
        }
        return null;
    }
}
