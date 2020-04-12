package co.lilpilot.postcard.postcontext.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostEntityTest {

    @Test
    void should_set_title_and_content_when_create() {
        PostEntity postEntity = new PostEntity("测试标题", "测试内容");
        assertThat(postEntity.getTitle()).isEqualTo("测试标题");
        assertThat(postEntity.getContent()).isEqualTo("测试内容");
    }

    @ParameterizedTest
    @CsvSource({
            " , 测试内容",
            "'', 测试内容",
            "测试标题,  ",
            "测试标题, ''"
    })
    void should_throw_exception_when_create_with_null_or_empty_param(String title, String content) {
        assertThatThrownBy(() -> new PostEntity(title, content))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void should_modify_title_and_content_when_modify() {
        PostEntity postEntity = new PostEntity("测试标题", "测试内容");
        postEntity.modify("测试标题2", "测试内容2");
        assertThat(postEntity.getTitle()).isEqualTo("测试标题2");
        assertThat(postEntity.getContent()).isEqualTo("测试内容2");
    }

    @ParameterizedTest
    @CsvSource({
            " , 测试内容",
            "'', 测试内容",
            "测试标题,  ",
            "测试标题, ''"
    })
    void should_throw_exception_when_modify_with_null_or_empty_param(String title, String content) {
        PostEntity postEntity = new PostEntity("测试标题", "测试内容");
        assertThatThrownBy(() -> postEntity.modify(title, content))
                .isInstanceOf(RuntimeException.class);
    }
}