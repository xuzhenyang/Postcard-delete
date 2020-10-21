package co.lilpilot.postcard.tagconfigcontext.interfaces;

import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigDeleteEvent;
import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigEditEvent;

public interface ApplicationEventPublisher {
    void publishTagConfigDelete(TagConfigDeleteEvent tagConfigDeleteEvent);
    void publishTagConfigEdit(TagConfigEditEvent tagConfigEditEvent);
}
