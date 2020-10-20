package co.lilpilot.postcard.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class IdentifiedDomainObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public IdentifiedDomainObject() {
        super();
    }

    protected Long getId() {
        return this.id;
    }

    protected void setId(Long anId) {
        this.id = anId;
    }
}
