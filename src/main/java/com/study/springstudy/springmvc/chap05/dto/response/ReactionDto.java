package com.study.springstudy.springmvc.chap05.dto.response;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionDto {

    private boolean success; // 성공여부
    private int likeCount;  // 총 좋아요 수
    private int dislikeCount; // 총 싫어요 수
    private String userReaction; // 유저가 선택한 리액션 버튼
}
