package com.paras.linkedInProject.postsService.client;

import com.paras.linkedInProject.postsService.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connections-service", path = "/connections")
public interface ConnectionServiceClient {
    @GetMapping("/core/{userId}/first-degree")
    List<PersonDto> getFirstDegreeConnectionOfUser(@PathVariable("userId") Long userId);

}
