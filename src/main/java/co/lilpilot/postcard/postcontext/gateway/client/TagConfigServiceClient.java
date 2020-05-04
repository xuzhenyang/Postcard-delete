package co.lilpilot.postcard.postcontext.gateway.client;

import co.lilpilot.postcard.postcontext.domain.Tag;
import co.lilpilot.postcard.postcontext.interfaces.client.TagConfigClient;
import co.lilpilot.postcard.tagconfigcontext.application.TagConfigAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagConfigServiceClient implements TagConfigClient {

    @Autowired
    private TagConfigAppService tagConfigAppService;

    @Override
    public Tag getTagByCode(String tagCode) {
        //TODO 校验逻辑
      String name = tagConfigAppService.getNameByCode(tagCode);
        return new Tag(tagCode, name);
    }
}
