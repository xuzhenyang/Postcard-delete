package co.lilpilot.postcard.postcontext.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @OneToMany
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

    public void publish() {
        this.status = PostStatus.PUBLIC.getValue();
    }

    public void withdraw() {
        this.status = PostStatus.DRAFT.getValue();
    }
}
