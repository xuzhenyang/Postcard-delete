package co.lilpilot.postcard.postcontext.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    /**
     * @see PostStatus
     */
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<Tag> tagList;
    @CreatedDate
    private Date dateCreate;
    @LastModifiedDate
    private Date dateUpdate;

    public Post(String title, String content) {
        if (StringUtils.isEmpty(title)) {
            throw new RuntimeException("标题不能为空");
        }
        if (StringUtils.isEmpty(content)) {
            throw new RuntimeException("内容不能为空");
        }
        this.title = title;
        this.content = content;
        this.status = PostStatus.DRAFT.getValue();
    }

    public Post(String title, List<Tag> tagList, String content) {
        if (StringUtils.isEmpty(title)) {
            throw new RuntimeException("标题不能为空");
        }
        if (StringUtils.isEmpty(content)) {
            throw new RuntimeException("内容不能为空");
        }
        this.title = title;
        this.content = content;
        this.tagList = tagList;
        this.status = PostStatus.DRAFT.getValue();
    }


    public void edit(String title, List<Tag> tagList, String content) {
        if (StringUtils.isEmpty(title)) {
            throw new RuntimeException("标题不能为空");
        }
        if (StringUtils.isEmpty(content)) {
            throw new RuntimeException("内容不能为空");
        }
        this.title = title;
        this.content = content;
        this.tagList = tagList;
    }

    public void removeTag(String tagCode) {
        if (StringUtils.isEmpty(tagCode)) {
            throw new RuntimeException("标签编码不能为空");
        }
        if (CollectionUtils.isEmpty(tagList)) {
            throw new RuntimeException("标签列表为空");
        }
        tagList.removeIf(tag -> Objects.equals(tag.getCode(), tagCode));
    }

    public void publish() {
        this.status = PostStatus.PUBLIC.getValue();
    }

    public void withdraw() {
        this.status = PostStatus.DRAFT.getValue();
    }
}
