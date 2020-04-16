package co.lilpilot.postcard.tagconfigcontext.domain;

import co.lilpilot.postcard.BaseMockitoTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


public class TagConfigServiceTest extends BaseMockitoTest {

    @Mock
    TagConfigRepository tagConfigRepository;

    @InjectMocks
    TagConfigService tagConfigService;

    @Test
    void should_throw_exception_when_tag_config_exist() {
        //given
        String code = "test_code";
        String name = "test_name";
        TagConfig existTagConfig = new TagConfig(code, name);
        given(tagConfigRepository.findByCode(code))
                .willReturn(existTagConfig);
        //when
        //then
        assertThatThrownBy(() -> tagConfigService.addTagConfig(code, name))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("标签配置已存在");
    }

    @Test
    void should_save_when_tag_config_not_exist() {
        //given
        String code = "test_code";
        String name = "test_name";
        given(tagConfigRepository.findByCode(code))
                .willReturn(null);
        given(tagConfigRepository.save(any(TagConfig.class)))
                .willReturn(new TagConfig(code, name));
        //when
        TagConfig result = tagConfigService.addTagConfig(code, name);
        //then
        then(tagConfigRepository).should(times(1)).save(any(TagConfig.class));
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(code);
        assertThat(result.getName()).isEqualTo(name);
    }
}
