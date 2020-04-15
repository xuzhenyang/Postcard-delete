package co.lilpilot.postcard.postcontext.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostStatus {
    DRAFT(1, "草稿"),
    PUBLIC(2, "公开");

    private Integer value;
    private String name;


}
