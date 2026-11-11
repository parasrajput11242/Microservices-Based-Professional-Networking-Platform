package com.paras.linkedInProject.postsService.event;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class PostCreated {

    private Long ownerUserId;
    private Long postId;
    private Long userId;
    private String content;
}
