package co.lilpilot.postcard.tagconfigcontext.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagConfigRepository extends JpaRepository<TagConfig, Long> {
    TagConfig findByCode(String code);
}
