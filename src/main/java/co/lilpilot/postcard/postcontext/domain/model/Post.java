package co.lilpilot.postcard.postcontext.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
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
    }

    public void edit(String title, String content) {
        if (StringUtils.isEmpty(title)) {
            throw new RuntimeException("标题不能为空");
        }
        if (StringUtils.isEmpty(content)) {
            throw new RuntimeException("内容不能为空");
        }
        this.title = title;
        this.content = content;
    }
}
