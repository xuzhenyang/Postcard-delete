package co.lilpilot.postcard.tagconfigcontext.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagConfigRepository extends JpaRepository<TagConfig, Long> {
    TagConfig findByCode(String code);
}
