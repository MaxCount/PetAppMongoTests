package com.petapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Getter
@Setter
@ToString
@Document(collection = "Comments")
public class Comment {

    @Id
    @JsonIgnore
    private String commentId;
    @NotEmpty
    private String text;
    private Instant createdDate;
    private String creatorName;



}
