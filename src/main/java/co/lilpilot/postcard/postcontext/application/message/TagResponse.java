package co.lilpilot.postcard.postcontext.application.message;

import co.lilpilot.postcard.postcontext.domain.Tag;
import lombok.Getter;

@Getter
public class TagResponse {

    private Long id;
    private String code;
    private String name;

    private TagResponse(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public static TagResponse of(Tag tag) {
        if (tag == null) {
            return null;
        }
        return new TagResponse(tag.getId(), tag.getCode(), tag.getName());
    }
}
