package co.lilpilot.postcard.authcontext.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String account;
    private String password;
    @CreatedDate
    private Date dateCreate;
    @LastModifiedDate
    private Date dateUpdate;

    //TODO for test
    public User(Long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }
}
