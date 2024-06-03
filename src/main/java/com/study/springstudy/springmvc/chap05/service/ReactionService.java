package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.dto.response.ReactionDto;
import com.study.springstudy.springmvc.chap05.entity.Reaction;
import com.study.springstudy.springmvc.chap05.entity.ReactionType;
import com.study.springstudy.springmvc.chap05.mapper.ReactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionMapper reactionMapper;

    // 공통 리액션 처리 메서드
    private Reaction handleReaction(int boardNo, String account, ReactionType newReactionType) {

        // 현재 게시물의 리액션 조회
        Reaction existingReaction = reactionMapper.findReaction(boardNo, account);

        if (existingReaction != null) {
            if (existingReaction.getReactionType().equals(newReactionType)) {
                reactionMapper.delete(boardNo, account); // 동일한 리액션이면 취소
            } else {
                reactionMapper.delete(boardNo, account); // 반대 리액션이면 취소하고
                saveReaction(boardNo, account, newReactionType); // 새 리액션으로 변경
            }
        } else {
            saveReaction(boardNo, account, newReactionType); // 처음 누른 경우
        }

        Reaction newReaction = reactionMapper.findReaction(boardNo, account);

        return newReaction;
    }

    // 리액션 저장 메서드
    private void saveReaction(int boardNo, String account, ReactionType reactionType) {
        Reaction newReaction = Reaction.builder()
                .boardNo(boardNo)
                .account(account)
                .reactionType(reactionType)
                .build();
        reactionMapper.save(newReaction);
    }

    public ReactionDto like(int boardNo, String account) {
        Reaction reaction = handleReaction(boardNo, account, ReactionType.LIKE);
        return getReactionDto(boardNo, reaction);
    }

    public ReactionDto dislike(int boardNo, String account) {
        Reaction reaction = handleReaction(boardNo, account, ReactionType.DISLIKE);
        return getReactionDto(boardNo, reaction);
    }

    private ReactionDto getReactionDto(int boardNo, Reaction reaction) {

        ReactionType userReaction = null;

        if (reaction != null) userReaction = reaction.getReactionType();

        return ReactionDto.builder()
                .success(true)
                .likeCount(reactionMapper.countLikes(boardNo))
                .dislikeCount(reactionMapper.countDislikes(boardNo))
                .userReaction(userReaction != null ? userReaction.toString() : null)
                .build();
    }


}
