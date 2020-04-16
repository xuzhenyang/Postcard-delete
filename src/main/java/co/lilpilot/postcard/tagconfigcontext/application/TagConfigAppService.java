package co.lilpilot.postcard.tagconfigcontext.application;

import co.lilpilot.postcard.tagconfigcontext.domain.TagConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TagConfigAppService {

    @Autowired
    private TagConfigService tagConfigService;

    @Transactional
    public void addTagConfig(String code, String name) {
        tagConfigService.addTagConfig(code, name);
    }

    @Transactional
    public void deleteTagConfig(Long id) {
        tagConfigService.delete(id);
    }
}
