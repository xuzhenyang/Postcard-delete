package co.lilpilot.postcard.postcontext.application.message;

import co.lilpilot.postcard.postcontext.domain.Post;
import co.lilpilot.postcard.postcontext.domain.PostStatus;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String status;
    private String statusDesc;
    private List<TagResponse> tagList;
    private Date dateCreate;
    private Date dateUpdate;

    public static PostResponse of(Post post) {
        if (post == null) {
            return null;
        }
        List<TagResponse> tagResponseList = CollectionUtils.isEmpty(post.getTagList()) ? null :
                post.getTagList().stream().map(TagResponse::of).collect(Collectors.toList());
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(),
                post.getStatus(), tagResponseList, post.getDateCreate(), post.getDateUpdate());
    }

    private PostResponse(Long id, String title, String content, PostStatus status, List<TagResponse> tagList, Date dateCreate, Date dateUpdate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status.name();
        this.statusDesc = status.getDesc();
        this.tagList = tagList;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
    }
}
