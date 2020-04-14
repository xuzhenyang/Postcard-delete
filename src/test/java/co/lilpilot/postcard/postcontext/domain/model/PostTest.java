package co.lilpilot.postcard.postcontext.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostTest {

    @Test
    void should_set_title_and_content_when_create() {
        Post post = new Post("测试标题", "测试内容");
        assertThat(post.getTitle()).isEqualTo("测试标题");
        assertThat(post.getContent()).isEqualTo("测试内容");
    }

    @ParameterizedTest
    @CsvSource({
            " , 测试内容",
            "'', 测试内容",
            "测试标题,  ",
            "测试标题, ''"
    })
    void should_throw_exception_when_create_with_null_or_empty_param(String title, String content) {
        assertThatThrownBy(() -> new Post(title, content))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void should_edit_title_and_content_when_modify() {
        Post post = new Post("测试标题", "测试内容");
        post.edit("测试标题2", "测试内容2");
        assertThat(post.getTitle()).isEqualTo("测试标题2");
        assertThat(post.getContent()).isEqualTo("测试内容2");
    }

    @ParameterizedTest
    @CsvSource({
            " , 测试内容",
            "'', 测试内容",
            "测试标题,  ",
            "测试标题, ''"
    })
    void should_throw_exception_when_edit_with_null_or_empty_param(String title, String content) {
        Post post = new Post("测试标题", "测试内容");
        assertThatThrownBy(() -> post.edit(title, content))
                .isInstanceOf(RuntimeException.class);
    }
}