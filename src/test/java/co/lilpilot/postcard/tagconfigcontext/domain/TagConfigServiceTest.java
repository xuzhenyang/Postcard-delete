package co.lilpilot.postcard.tagconfigcontext.domain;

import co.lilpilot.postcard.BaseMockitoTest;
import co.lilpilot.postcard.tagconfigcontext.interfaces.ApplicationEventPublisher;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


public class TagConfigServiceTest extends BaseMockitoTest {

    @Mock
    TagConfigRepository tagConfigRepository;
    @Mock
    ApplicationEventPublisher applicationEventPublisher;
    @InjectMocks
    TagConfigService tagConfigService;

    @Captor
    ArgumentCaptor<TagConfig> tagConfigArgumentCaptor;

    @Test
    void should_throw_exception_when_add_and_code_config_exist() {
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
    void should_throw_exception_when_add_and_name_config_exist() {
        //given
        String code = "test_code";
        String name = "test_name";
        TagConfig existTagConfig = new TagConfig(code, name);
        given(tagConfigRepository.findByName(name))
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

    @Test
    void test_delete_by_id() {
        //given
        String code = "test_code";
        String name = "test_name";
        TagConfig tagConfig = new TagConfig(code, name);
        long tagId = 1L;
        given(tagConfigRepository.findById(tagId))
                .willReturn(Optional.of(tagConfig));
        //when
        TagConfig deletedConfig = tagConfigService.delete(tagId);
        //then
        then(tagConfigRepository).should(times(1))
                .deleteById(tagId);
        assertThat(deletedConfig.getCode()).isEqualTo(code);
        assertThat(deletedConfig.getName()).isEqualTo(name);
    }

    @Test
    void should_throw_exception_when_delete_and_config_not_exist() {
        //given
        long tagId = 1L;
        given(tagConfigRepository.findById(tagId))
                .willReturn(Optional.empty());
        //when
        assertThatThrownBy(() -> tagConfigService.delete(tagId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("标签配置不存在");
    }

    @Test
    void test_edit_tag_config_name() {
        //given
        String code = "test_code";
        String name = "test_name";
        TagConfig tagConfig = new TagConfig(code, name);
        long tagId = 1L;
        given(tagConfigRepository.findById(tagId))
                .willReturn(Optional.of(tagConfig));
        //when
        String newName = "new_name";
        tagConfigService.editName(tagId, newName);
        //then
        then(tagConfigRepository).should().save(tagConfigArgumentCaptor.capture());
        TagConfig result = tagConfigArgumentCaptor.getValue();
        assertThat(result.getCode()).isEqualTo(code);
        assertThat(result.getName()).isEqualTo(newName);
    }

    @Test
    void should_throw_exception_when_edit_and_name_already_exist() {
        //given
        String code = "test_code";
        String name = "test_name";
        TagConfig tagConfig = new TagConfig(code, name);
        long tagId = 1L;
        given(tagConfigRepository.findByName(name))
                .willReturn(tagConfig);
        //when
        //then
        assertThatThrownBy(() -> tagConfigService.editName(tagId, name))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("名称已存在");
    }
}
