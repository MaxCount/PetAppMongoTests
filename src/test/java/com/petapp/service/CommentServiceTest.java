package com.petapp.service;


import com.petapp.dto.CommentRequest;
import com.petapp.entity.Comment;
import com.petapp.persistance.CommentRepository;
import org.assertj.core.api.Assertions;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentService commentService;

    // here we test the ability of our service to save a
    // comment with known entity and finding comment by id
    // if test pass -> method save comment work correctly
    // and get comment method returns the right value
    @Test
    void itShouldCheckPostAndGetCommentTest() {

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setText("TestText");
        commentRequest.setCreatorName("TestUser");


        Comment comment = new Comment();
        comment.setCommentId("6346f7d8469db26b280335d6");
        comment.setText("TestText");
        comment.setCreatorName("TestUser");
        comment.setCreatedDate(Instant.now());

        //testing ability to save comment
        commentService.save(commentRequest);
        Assertions.assertThat(commentService).isNotNull();

        ObjectId objId = new ObjectId("6346f7d8469db26b280335d6");

        Mockito.when(commentRepository.findById(objId)).thenReturn(java.util.Optional.of(comment));

        Optional<Comment> comment1 = commentService.getComment("6346f7d8469db26b280335d6");

        //checking that returns the right value
        Assertions.assertThat(comment1).isNotNull();
        Assertions.assertThat("TestText").isEqualTo(comment1.orElseThrow().getText());
    }
}