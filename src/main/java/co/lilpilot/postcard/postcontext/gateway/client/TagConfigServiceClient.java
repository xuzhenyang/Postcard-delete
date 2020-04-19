package co.lilpilot.postcard.postcontext.gateway.client;

import co.lilpilot.postcard.postcontext.interfaces.client.TagConfigClient;
import co.lilpilot.postcard.tagconfigcontext.application.TagConfigAppService;
import org.springframework.beans.factory.annotation.Autowired;

public class TagConfigServiceClient implements TagConfigClient {

    @Autowired
    private TagConfigAppService tagConfigAppService;

    @Override
    public String getTagNameByCode(String tagCode) {
        return tagConfigAppService.getNameByCode(tagCode);
    }
}
