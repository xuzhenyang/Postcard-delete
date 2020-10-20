package co.lilpilot.postcard.postcontext.application.message;

import co.lilpilot.postcard.postcontext.domain.Tag;
import lombok.Getter;

@Getter
public class TagResponse {

    private String code;
    private String name;

    private TagResponse(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TagResponse of(Tag tag) {
        if (tag == null) {
            return null;
        }
        return new TagResponse(tag.getCode(), tag.getName());
    }
}
