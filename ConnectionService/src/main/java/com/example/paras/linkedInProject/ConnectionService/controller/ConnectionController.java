package com.example.paras.linkedInProject.ConnectionService.controller;

import com.example.paras.linkedInProject.ConnectionService.entities.User;
import com.example.paras.linkedInProject.ConnectionService.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/core")
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{userId}/first-degree")

    public ResponseEntity<List<User>> getFirstDegreeConnectionOfUser(@PathVariable Long userId, @RequestHeader("X-User-Id") Long headerUserId){
        log.info("User id is {}",userId);
        List<User> list = connectionService.getFirstDegreeConnectionsOfUser(userId);
        return ResponseEntity.ok(list);
    }
}
