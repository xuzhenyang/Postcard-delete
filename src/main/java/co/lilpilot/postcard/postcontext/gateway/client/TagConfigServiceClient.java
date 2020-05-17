package co.lilpilot.postcard.postcontext.gateway.client;

import co.lilpilot.postcard.postcontext.domain.Tag;
import co.lilpilot.postcard.postcontext.interfaces.client.TagConfigClient;
import co.lilpilot.postcard.tagconfigcontext.application.TagConfigAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TagConfigServiceClient implements TagConfigClient {

    @Autowired
    private TagConfigAppService tagConfigAppService;

    @Override
    public Tag getTagByCode(String tagCode) {
        String name = tagConfigAppService.getNameByCode(tagCode);
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("标签配置不存在");
        }
        return new Tag(tagCode, name);
    }
}
