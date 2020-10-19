package co.lilpilot.postcard.postcontext.domain;

import co.lilpilot.postcard.BaseMockitoTest;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


public class PostServiceTest extends BaseMockitoTest {

    @Mock
    PostRepository postRepository;
    @InjectMocks
    PostService postService;
    @Captor
    ArgumentCaptor<Post> postArgumentCaptor;

    @Test
    public void test_create_post() {
        //given
        Post post = new Post(
                "post",
                Lists.newArrayList(
                        new Tag("tagCode", "测试标签1"),
                        new Tag("test_tag_2", "测试标签2")),
                "测试内容1");
        given(postRepository.save(post))
                .willReturn(post);
        //when
        Post result = postService.createPost(post);
        //then
        then(postRepository).should().save(post);
        assertThat(result.getTitle()).isEqualTo(post.getTitle());
    }

    @Test
    public void test_update_post() {
        //given
        List<Tag> tagList = Lists.newArrayList(
                new Tag("tag_1", "测试标签1"),
                new Tag("tag_2", "测试标签2"));
        Post post = new Post(
                "title",
                tagList,
                "测试内容");
        given(postRepository.findById(anyLong()))
                .willReturn(Optional.of(post));
        //when
        tagList.add(new Tag("tag_3", "测试标签2"));
        postService.editPost(1L, "updated_title", tagList, "updated_content");
        //then
        then(postRepository).should().save(postArgumentCaptor.capture());
        Post result = postArgumentCaptor.getValue();
        assertThat(result.getTitle()).isEqualTo("updated_title");
        assertThat(result.getTagList().size()).isEqualTo(3);
        assertThat(result.getContent()).isEqualTo("updated_content");
    }

    @Test
    public void test_remove_tag() {
        //given
        String tagCode = "test_tag";
        Post post1 = new Post(
                "post_1",
                Lists.newArrayList(
                        new Tag(tagCode, "测试标签1"),
                        new Tag("test_tag_2", "测试标签2")),
                "测试内容1");
        Post post2 = new Post(
                "post_2",
                Lists.newArrayList(new Tag("test_tag_3", "测试标签3")),
                "测试内容2");
        given(postRepository.findAll())
                .willReturn(Lists.newArrayList(post1, post2));
        //when
        postService.removeTag(tagCode);
        //then
        assertThat(post1.getTagList().size()).isEqualTo(1);
        assertThat(post2.getTagList().size()).isEqualTo(1);
    }

}
