package com.paras.linkedInProject.postsService.auth;

import feign.RequestInterceptor;

public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(feign.RequestTemplate template) {
        Long userId = AuthContextHolder.getCurrentUserId();
        if(userId != null){
            template.header("X-User-Id",userId.toString());
        }
    }
}
