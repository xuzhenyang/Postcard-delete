package co.lilpilot.postcard.postcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private PostStatus status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    @JsonIgnore
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
        this.status = PostStatus.DRAFT;
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
        this.status = PostStatus.DRAFT;
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
        if (CollectionUtils.isEmpty(this.tagList)) {
            this.tagList = Lists.newArrayList();
        }
        this.tagList.clear();
        if (!CollectionUtils.isEmpty(tagList)) {
            this.tagList.addAll(tagList);
        }
    }

    public void addTag(String tagCode, String tagName) {
        if (StringUtils.isEmpty(tagCode) || StringUtils.isEmpty(tagName)) {
            throw new RuntimeException("标签信息不能为空");
        }
        if (CollectionUtils.isEmpty(this.tagList)) {
            this.tagList = Lists.newArrayList();
        }
        this.tagList.add(new Tag(tagCode, tagName));
    }

    public void removeTag(String tagCode) {
        if (StringUtils.isEmpty(tagCode)) {
            throw new RuntimeException("标签编码不能为空");
        }
        if (CollectionUtils.isEmpty(this.tagList)) {
            throw new RuntimeException("标签列表为空");
        }
        this.tagList.removeIf(tag -> Objects.equals(tag.getCode(), tagCode));
    }

    public void publish() {
        this.status = PostStatus.PUBLIC;
    }

    public void withdraw() {
        this.status = PostStatus.DRAFT;
    }
}
