package com.codebenders.sunnywalkbackend.controller;

import com.codebenders.sunnywalkbackend.dto.WalkSuggestionDto;
import com.codebenders.sunnywalkbackend.service.IWalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
