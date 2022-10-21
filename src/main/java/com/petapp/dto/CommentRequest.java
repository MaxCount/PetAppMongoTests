package com.petapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    @NotNull
    private String text;
    @NotNull
    private String creatorName;
}
