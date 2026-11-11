package com.paras.linkedInProject.postsService.service;

import ch.qos.logback.core.model.Model;
import com.paras.linkedInProject.postsService.auth.AuthContextHolder;
import com.paras.linkedInProject.postsService.client.ConnectionServiceClient;
import com.paras.linkedInProject.postsService.dto.PersonDto;
import com.paras.linkedInProject.postsService.dto.PostCreateRequestDto;
import com.paras.linkedInProject.postsService.dto.PostDto;
import com.paras.linkedInProject.postsService.entity.Post;
import com.paras.linkedInProject.postsService.event.PostCreated;
import com.paras.linkedInProject.postsService.exception.ResourceNotFoundException;
import com.paras.linkedInProject.postsService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ConnectionServiceClient connectionServiceClient;
    private final KafkaTemplate<Long, PostCreated> postCreatedKafkaTemplate;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto , Long userId) {
        log.info("Creating post for user with id: {}",userId );
        Post post = modelMapper.map(postCreateRequestDto,Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        log.info("Post Created");
        List<PersonDto> personDtos = connectionServiceClient.getFirstDegreeConnectionOfUser(userId);

        for(PersonDto personDto : personDtos){
            PostCreated postCreated = PostCreated.builder()
                    .postId(post.getId())
                    .content(post.getContent())
                    .userId(personDto.getUserId())
                    .ownerUserId(userId)
                    .build();
            postCreatedKafkaTemplate.send("post-created-topic",postCreated);
        }
        log.info("Notification Send");
        return modelMapper.map(post,PostDto.class);

    }

    public PostDto getPostById(Long postId) {
        log.info("Getting the post with ID : {}",postId);
        Long userId = AuthContextHolder.getCurrentUserId();

        List<PersonDto> personDtos = connectionServiceClient.getFirstDegreeConnectionOfUser(userId);

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Not found with id "+  postId));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all the posts of a user with ID:{}",userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList
                .stream()
                .map(post -> modelMapper.map(post,PostDto.class))
                .toList();
        // different
    }
}
