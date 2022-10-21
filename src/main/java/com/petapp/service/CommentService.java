package com.petapp.service;

import com.petapp.dto.CommentRequest;
import com.petapp.entity.Comment;
import com.petapp.persistance.CommentRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(CommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setCreatorName(commentRequest.getCreatorName());
        comment.setText(commentRequest.getText());
        comment.setCreatedDate(Instant.now());
        commentRepository.save(comment);
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public Optional<Comment> getComment(String id){
        return commentRepository.findById(new ObjectId(id));
   }

    public void updateComment(Comment newComment, String id){
        commentRepository.findById(new ObjectId(id))
                .map(comment -> {
                    comment.setText(newComment.getText());
                    return commentRepository.save(comment);
                });
    }

    public void deleteComment(String id){
        commentRepository.deleteById(new ObjectId(id));
    }
}
