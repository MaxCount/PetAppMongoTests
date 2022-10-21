package com.petapp.service;


import com.petapp.entity.Comment;
import com.petapp.persistance.CommentRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class CommentServiceTest {


    @Mock CommentRepository commentRepository;

    @Test
    void saveCommentTest() {

        Comment comment = new Comment();
        comment.setCommentId("6346f7d8469db26b280335d6");
        comment.setCreatorName("TestUser");
        comment.setText("TestText");
        comment.setCreatedDate(Instant.now());
        commentRepository.save(comment);

        when(commentRepository.findById(new ObjectId("6346f7d8469db26b280335d6")));
        assertEquals(comment, commentRepository.findById(new ObjectId("6346f7d8469db26b280335d6")));

    }

    @Test
    void getComments() {
    }

    @Test
    void getComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }
}