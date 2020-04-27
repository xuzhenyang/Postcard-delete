package co.lilpilot.postcard.tagconfigcontext.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
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
}
