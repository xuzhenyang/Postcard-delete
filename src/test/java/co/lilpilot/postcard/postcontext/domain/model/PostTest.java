package co.lilpilot.postcard.postcontext.domain.model;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostTest {

    @Test
    void should_set_info_when_create() {
        Post post = new Post("测试标题", "测试内容");
        assertThat(post.getTitle()).isEqualTo("测试标题");
        assertThat(post.getContent()).isEqualTo("测试内容");
        assertThat(post.getStatus()).isEqualTo(PostStatus.DRAFT.getValue());
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
    void should_edit_title_and_content() {
        Post post = new Post("测试标题", "测试内容");
        post.edit("测试标题2", null, "测试内容2");
        assertThat(post.getTitle()).isEqualTo("测试标题2");
        assertThat(post.getContent()).isEqualTo("测试内容2");
    }

    @Test
    void should_edit_tag() {
        List<Tag> tagList = Lists.newArrayList(
                new Tag("tag_1", "标签1"),
                new Tag("tag_2", "标签2")
        );
        Post post = new Post("测试标题", tagList, "测试内容");
        assertThat(post.getTagList().size()).isEqualTo(2);
        tagList.add(new Tag("tag_3", "标签3"));
        post.edit("测试标题2", tagList, "测试内容2");
        assertThat(post.getTagList().size()).isEqualTo(3);
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
        assertThatThrownBy(() -> post.edit(title, null, content))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void should_change_status_to_public_when_publish() {
        Post post = new Post("测试标题", "测试内容");
        post.publish();
        assertThat(post.getStatus()).isEqualTo(PostStatus.PUBLIC.getValue());
    }

    @Test
    void should_change_status_to_draft_when_withdraw() {
        Post post = new Post("测试标题", "测试内容");
        post.publish();
        assertThat(post.getStatus()).isEqualTo(PostStatus.PUBLIC.getValue());
        post.withdraw();
        assertThat(post.getStatus()).isEqualTo(PostStatus.DRAFT.getValue());
    }
}