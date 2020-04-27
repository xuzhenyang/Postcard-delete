package co.lilpilot.postcard.tagconfigcontext.application;

import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigDeleteEvent;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfig;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfigRepository;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfigService;
import co.lilpilot.postcard.tagconfigcontext.interfaces.ApplicationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class TagConfigAppService {

    @Autowired
    private TagConfigRepository tagConfigRepository;
    @Autowired
    private TagConfigService tagConfigService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void addTagConfig(String code, String name) {
        tagConfigService.addTagConfig(code, name);
    }

    @Transactional
    public void deleteTagConfig(Long id) {
        tagConfigService.delete(id);
        applicationEventPublisher.publishTagConfigDelete(new TagConfigDeleteEvent(id));
    }

    public String getNameByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        TagConfig tagConfig = tagConfigRepository.findByCode(code);
        return Objects.nonNull(tagConfig) ? tagConfig.getName() : null;
    }
}
