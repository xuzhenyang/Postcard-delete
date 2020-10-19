package co.lilpilot.postcard.postcontext.application.message;

import co.lilpilot.postcard.postcontext.domain.Tag;
import lombok.Data;

@Data
public class TagParam {
    private String code;
    private String name;

    public Tag toTag() {
        return new Tag(code, name);
    }
}
