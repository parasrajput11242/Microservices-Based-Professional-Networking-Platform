package com.example.paras.linkedInProject.ConnectionService.repositories;

import com.example.paras.linkedInProject.ConnectionService.entities.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<User, Long> {

    Optional<User> findByUserId(Long id);


    @Query("""
    MATCH (personA:User)-[:CONNECTED_TO]-(personB:User)
    WHERE personA.userId = $userId
    RETURN personB
""")
    List<User> getFirstDegreeConnections(@Param("userId") Long userId);


}
