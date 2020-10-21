package co.lilpilot.postcard.tagconfigcontext.application;

import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigDeleteEvent;
import co.lilpilot.postcard.tagconfigcontext.application.message.TagConfigCreateRequest;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfig;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfigRepository;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfigService;
import co.lilpilot.postcard.tagconfigcontext.interfaces.ApplicationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TagConfigAppService {

    @Autowired
    private TagConfigRepository tagConfigRepository;
    @Autowired
    private TagConfigService tagConfigService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public List<TagConfig> listAll() {
        return tagConfigRepository.findAll();
    }

    @Transactional
    public void addTagConfig(TagConfigCreateRequest request) {
        tagConfigService.addTagConfig(request.getCode(), request.getName());
    }

    @Transactional
    public void deleteTagConfig(Long id) {
        TagConfig deletedTagConfig = tagConfigService.delete(id);
        applicationEventPublisher.publishTagConfigDelete(new TagConfigDeleteEvent(deletedTagConfig.getCode()));
    }

    @Transactional
    public void editTagConfigName(Long id, String name) {
        tagConfigService.editName(id, name);
        //TODO 发布事件通知post context更新
    }

    public String getNameByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        TagConfig tagConfig = tagConfigRepository.findByCode(code);
        return Objects.nonNull(tagConfig) ? tagConfig.getName() : null;
    }
}
