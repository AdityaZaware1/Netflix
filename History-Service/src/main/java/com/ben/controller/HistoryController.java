package com.ben.controller;

import com.ben.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(historyService.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.getHistoryByUserId(id));
    }
}
