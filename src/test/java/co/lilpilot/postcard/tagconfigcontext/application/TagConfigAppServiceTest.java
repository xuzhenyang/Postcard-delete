package co.lilpilot.postcard.tagconfigcontext.application;

import co.lilpilot.postcard.BaseMockitoTest;
import co.lilpilot.postcard.tagconfigcontext.application.event.TagConfigDeleteEvent;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfig;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfigRepository;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfigService;
import co.lilpilot.postcard.tagconfigcontext.interfaces.ApplicationEventPublisher;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class TagConfigAppServiceTest extends BaseMockitoTest {

    @Mock
    TagConfigRepository tagConfigRepository;
    @Mock
    ApplicationEventPublisher applicationEventPublisher;
    @Mock
    TagConfigService tagConfigService;
    @InjectMocks
    TagConfigAppService tagConfigAppService;

    @Captor
    ArgumentCaptor<TagConfigDeleteEvent> tagConfigDeleteEventArgumentCaptor;

    @Test
    void delete_should_publish_event() {
        //given
        String code = "test_code";
        String name = "test_name";
        TagConfig tagConfig = new TagConfig(code, name);
        long tagId = 1L;
        given(tagConfigService.delete(tagId))
                .willReturn(tagConfig);
        //when
        tagConfigAppService.deleteTagConfig(tagId);
        //then
        then(tagConfigService).should(times(1))
                .delete(tagId);
        then(applicationEventPublisher).should(times(1))
                .publishTagConfigDelete(tagConfigDeleteEventArgumentCaptor.capture());
        TagConfigDeleteEvent tagConfigDeleteEvent = tagConfigDeleteEventArgumentCaptor.getValue();
        assertThat(tagConfigDeleteEvent.getTagCode()).isEqualTo(code);
    }
}
