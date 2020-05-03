package co.lilpilot.postcard.postcontext.gateway.event;

import co.lilpilot.postcard.postcontext.application.PostAppService;
import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigDeleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TagConfigEventHandler {

    @Autowired
    private PostAppService postAppService;

    @EventListener
    public void handleTagConfigDelete(TagConfigDeleteEvent tagConfigDeleteEvent) {
        String tagCode = tagConfigDeleteEvent.getTagCode();
        postAppService.removeTag(tagCode);
    }

}
