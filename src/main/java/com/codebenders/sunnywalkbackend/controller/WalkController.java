package com.codebenders.sunnywalkbackend.controller;

import com.codebenders.sunnywalkbackend.dto.AddWalkDto;
import com.codebenders.sunnywalkbackend.dto.WalkSuggestionDto;
import com.codebenders.sunnywalkbackend.service.IWalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/walk")
public class WalkController {

    @Autowired
    IWalkService walkService;

    @GetMapping("/suggestion/{userId}")
    public ArrayList<WalkSuggestionDto> getWalkSuggestion(@PathVariable int userId) {
        return walkService.getSuggestion(userId);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<String> addWalk(@RequestBody AddWalkDto addWalkDto, @PathVariable int userId) {
        walkService.addWalk(userId, addWalkDto);
        return new ResponseEntity<String>("Walk added successfully", HttpStatus.OK);
    }
}
