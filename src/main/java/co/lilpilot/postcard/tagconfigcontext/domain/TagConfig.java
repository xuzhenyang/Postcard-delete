package co.lilpilot.postcard.tagconfigcontext.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class TagConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String name;
    @CreatedDate
    private Date dateCreate;
    @LastModifiedDate
    private Date dateUpdate;

    public TagConfig(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public void edit(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("名称不能为空");
        }
        this.name = name;
    }
}
