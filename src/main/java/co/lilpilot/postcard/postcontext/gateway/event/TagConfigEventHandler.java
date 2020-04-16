package co.lilpilot.postcard.postcontext.gateway.event;

import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigDeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TagConfigEventHandler {

    @EventListener
    public void handleTagConfigDelete(TagConfigDeleteEvent tagConfigDeleteEvent) {
        Long tagConfigId = tagConfigDeleteEvent.getTagConfigId();
        //TODO 文章删除标签
    }

}
