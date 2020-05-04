package co.lilpilot.postcard.postcontext.application;

import co.lilpilot.postcard.BaseMockitoTest;
import co.lilpilot.postcard.postcontext.application.message.PostCreateRequest;
import co.lilpilot.postcard.postcontext.application.message.PostRequestAssembler;
import co.lilpilot.postcard.postcontext.domain.Post;
import co.lilpilot.postcard.postcontext.domain.PostService;
import co.lilpilot.postcard.postcontext.domain.Tag;
import co.lilpilot.postcard.postcontext.interfaces.client.TagConfigClient;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

public class PostAppServiceTest extends BaseMockitoTest {

    @Mock
    PostService postService;
    @Mock
    PostRequestAssembler postRequestAssembler;
    @InjectMocks
    PostAppService postAppService;

    @Captor
    ArgumentCaptor<Post> postArgumentCaptor;

    @Test
    public void test_create_post() {
        //given
        PostCreateRequest postCreateRequest = new PostCreateRequest();
        String title = "test_post";
        postCreateRequest.setTitle(title);
        String tagCode = "test_tag";
        postCreateRequest.setTagCodeList(Lists.newArrayList(tagCode));
        postCreateRequest.setContent("test_content");
        Tag tag = new Tag(tagCode, "test_tag");
        given(postRequestAssembler.toPost(postCreateRequest))
                .willReturn(new Post(title, Lists.newArrayList(tag), "111"));
        //when
        postAppService.createPost(postCreateRequest);
        //then
        then(postService).should().createPost(postArgumentCaptor.capture());
        Post post = postArgumentCaptor.getValue();
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getTagList().get(0)).isEqualTo(tag);
    }

    @Test
    public void test_remove_tag() {
        //given
        String tagCode = "test_code";
        //when
        postAppService.removeTag(tagCode);
        //then
        then(postService).should(times(1))
                .removeTag(tagCode);
    }
}
