package co.lilpilot.postcard.postcontext.domain;

import co.lilpilot.postcard.BaseMockitoTest;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


public class PostServiceTest extends BaseMockitoTest {

    @Mock
    PostRepository postRepository;
    @InjectMocks
    PostService postService;

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
