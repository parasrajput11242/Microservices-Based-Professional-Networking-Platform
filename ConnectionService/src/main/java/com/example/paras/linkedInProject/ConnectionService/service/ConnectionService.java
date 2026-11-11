package com.example.paras.linkedInProject.ConnectionService.service;

import com.example.paras.linkedInProject.ConnectionService.entities.User;
import com.example.paras.linkedInProject.ConnectionService.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionService {

    private final PersonRepository personRepository;

    public List<User> getFirstDegreeConnectionsOfUser(Long userId) {
        log.info("Getting the first Degree Connections of user with Id {}",userId);
        return personRepository.getFirstDegreeConnections(userId);
    }
}
