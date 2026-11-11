package com.paras.linkedInProject.postsService.controller;

import com.paras.linkedInProject.postsService.auth.AuthContextHolder;
import com.paras.linkedInProject.postsService.dto.PostCreateRequestDto;
import com.paras.linkedInProject.postsService.dto.PostDto;
import com.paras.linkedInProject.postsService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.util.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest){
        Long userId  = AuthContextHolder.getCurrentUserId();
        PostDto postDto = postService.createPost(postCreateRequestDto,userId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "postId") Long postId){

        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPosts(@PathVariable(name = "userId") Long userId){
        List<PostDto> postDtoList = postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(postDtoList);
    }
}
