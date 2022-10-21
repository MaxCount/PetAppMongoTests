package com.petapp.controller;

import com.petapp.dto.CommentRequest;
import com.petapp.entity.Comment;
import com.petapp.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/petapp/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/postComment")
    public ResponseEntity<String> saveComment(@RequestBody CommentRequest commentRequest){
        commentService.save(commentRequest);
        return new ResponseEntity<>("Comment send successfully", OK);
    }

    @GetMapping("/getAllComments")
    public List<Comment> getAllComments(){
       return commentService.getComments();
    }

    @GetMapping("/getComment/{id}")
    public Optional<Comment> getCommentById(@PathVariable String id){
        return commentService.getComment(id);
    }

    @PutMapping("/updateComment/{id}")
    public void updateComment(@RequestBody Comment comment, @PathVariable String id){
        commentService.updateComment(comment, id);
    }

    @DeleteMapping("/deleteComment/{id}")
    public void deleteComment(@PathVariable String id){
        commentService.deleteComment(id);
    }
}
