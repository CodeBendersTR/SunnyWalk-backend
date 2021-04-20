package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.dto.WalkSuggestionDto;

import java.util.ArrayList;

public interface IWalkService {
    ArrayList<WalkSuggestionDto> getSuggestion(int userId);
}
