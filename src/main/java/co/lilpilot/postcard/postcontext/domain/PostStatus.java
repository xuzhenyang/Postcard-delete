package co.lilpilot.postcard.postcontext.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostStatus {
    DRAFT("草稿"),
    PUBLIC("公开");

    private String desc;


}
