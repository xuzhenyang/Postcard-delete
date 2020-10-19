package co.lilpilot.postcard.postcontext.application.message;

import co.lilpilot.postcard.postcontext.domain.Post;
import co.lilpilot.postcard.postcontext.domain.Tag;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostCreateRequest {
    private String title;
    private List<TagParam> tagParamList;
    private String content;

    public Post toPost() {
        List<Tag> tagList = CollectionUtils.isEmpty(tagParamList) ? null :
                tagParamList.stream().map(TagParam::toTag).collect(Collectors.toList());
        return new Post(title, tagList, content);
    }
}
