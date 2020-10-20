package co.lilpilot.postcard.postcontext.domain;

import co.lilpilot.postcard.core.IdentifiedValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Tag extends IdentifiedValueObject {

    private String code;
    private String name;

    public Tag(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
