package co.lilpilot.postcard.tagconfigcontext.gateway.event;

import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigDeleteEvent;
import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigEditEvent;
import co.lilpilot.postcard.tagconfigcontext.interfaces.ApplicationEventPublisher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringEventPublisher implements ApplicationEventPublisher, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void publishTagConfigDelete(TagConfigDeleteEvent tagConfigDeleteEvent) {
        applicationContext.publishEvent(tagConfigDeleteEvent);
    }

    @Override
    public void publishTagConfigEdit(TagConfigEditEvent tagConfigEditEvent) {
        applicationContext.publishEvent(tagConfigEditEvent);
    }

}
