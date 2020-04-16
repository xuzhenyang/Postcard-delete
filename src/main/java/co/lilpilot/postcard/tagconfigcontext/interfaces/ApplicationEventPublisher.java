package co.lilpilot.postcard.tagconfigcontext.interfaces;

import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigDeleteEvent;

public interface ApplicationEventPublisher {
    public void publishTagConfigDelete(TagConfigDeleteEvent tagConfigDeleteEvent);
}
