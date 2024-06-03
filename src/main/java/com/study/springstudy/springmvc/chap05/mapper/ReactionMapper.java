package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.entity.Reaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReactionMapper {

    // 리액션 등록
    void save(Reaction reaction);

    // 리액션 삭제
    void delete(@Param("boardNo") long boardNo, @Param("account") String account);


    // 특정 게시글의 좋아요 수 조회
    int countLikes(long boardNo);

    // 특정 게시글의 싫어요 수 조회
    int countDislikes(long boardNo);

    // 특정 회원이 특정 게시글에 리액션을 눌렀는지 확인
    Reaction findReaction(@Param("boardNo") long boardNo, @Param("account") String account);
}
