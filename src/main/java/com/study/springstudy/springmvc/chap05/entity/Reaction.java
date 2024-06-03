package com.study.springstudy.springmvc.chap05.entity;
/*
CREATE TABLE tbl_reaction (
    reaction_id INT(8) PRIMARY KEY AUTO_INCREMENT,
    board_no INT(8) NOT NULL,
    account VARCHAR(50) NOT NULL,
    reaction_type ENUM('LIKE', 'DISLIKE') NOT NULL,
    reaction_date DATETIME DEFAULT current_timestamp,
    CONSTRAINT fk_reaction_board FOREIGN KEY (board_no) REFERENCES tbl_board(board_no),
    CONSTRAINT fk_reaction_member FOREIGN KEY (account) REFERENCES tbl_member(account)
);

 */


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {

    private long reactionId; // 리액션 ID
    private long boardNo; // 게시글 번호
    private String account; // 회원 계정명
    private ReactionType reactionType; // 리액션 타입 ('LIKE' 또는 'DISLIKE')
    private LocalDateTime reactionDate; // 리액션 등록일시
}
