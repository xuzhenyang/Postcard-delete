package co.lilpilot.postcard.postcontext.domain;

import co.lilpilot.postcard.BaseMockitoTest;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class PostTest extends BaseMockitoTest {

    @Test
    void should_set_info_when_create() {
        Post post = new Post("测试标题", "测试内容");
        assertThat(post.getTitle()).isEqualTo("测试标题");
        assertThat(post.getContent()).isEqualTo("测试内容");
        assertThat(post.getStatus()).isEqualTo(PostStatus.DRAFT);
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
        List<Tag> updatedTagList = Lists.newArrayList(tagList);
        updatedTagList.add(new Tag("tag_3", "标签3"));
        post.edit("测试标题2", updatedTagList, "测试内容2");
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
        assertThat(post.getStatus()).isEqualTo(PostStatus.PUBLIC);
    }

    @Test
    void should_change_status_to_draft_when_withdraw() {
        Post post = new Post("测试标题", "测试内容");
        post.publish();
        assertThat(post.getStatus()).isEqualTo(PostStatus.PUBLIC);
        post.withdraw();
        assertThat(post.getStatus()).isEqualTo(PostStatus.DRAFT);
    }

    @Test
    void test_add_tag() {
        Post post = new Post("测试标题", null, "测试内容");
        post.addTag("tag_code", "tag_name");
        assertThat(post.getTagList().size()).isEqualTo(1);
        Tag actual = post.getTagList().get(0);
        assertThat(actual.getCode()).isEqualTo("tag_code");
        assertThat(actual.getName()).isEqualTo("tag_name");
    }

    @Test
    void test_remove_tag() {
        List<Tag> tagList = Lists.newArrayList(
                new Tag("tag_1", "标签1"),
                new Tag("tag_2", "标签2")
        );
        Post post = new Post("测试标题", tagList, "测试内容");
        assertThat(post.getTagList().size()).isEqualTo(2);
        post.removeTag("tag_1");
        assertThat(post.getTagList().size()).isEqualTo(1);
        assertThat(post.getTagList().get(0).getCode()).isEqualTo("tag_2");
    }

    @Test
    void test_edit_tag() {
        //given
        List<Tag> tagList = Lists.newArrayList(
                new Tag("tag_1", "标签1"),
                new Tag("tag_2", "标签2")
        );
        Post post = new Post("测试标题", tagList, "测试内容");
        //when
        post = post.editTag("tag_1", "变更内容");
        //then
        List<Tag> finalTagList = post.getTagList();
        assertThat(finalTagList.size()).isEqualTo(2);
        Optional<Tag> optionalTag1 = finalTagList.stream().filter(tag -> Objects.equals(tag.getCode(), "tag_1")).findFirst();
        assertThat(optionalTag1.isPresent()).isTrue();
        Tag tag1 = optionalTag1.get();
        assertThat(tag1.getName()).isEqualTo("变更内容");
        Optional<Tag> optionalTag2 = finalTagList.stream().filter(tag -> Objects.equals(tag.getCode(), "tag_2")).findFirst();
        assertThat(optionalTag2.isPresent()).isTrue();
        assertThat(optionalTag2.get().getName()).isEqualTo("标签2");
    }
}