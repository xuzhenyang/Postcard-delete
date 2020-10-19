package co.lilpilot.postcard.postcontext.application;

import co.lilpilot.postcard.BaseMockitoTest;
import co.lilpilot.postcard.postcontext.application.message.PostCreateRequest;
import co.lilpilot.postcard.postcontext.application.message.TagParam;
import co.lilpilot.postcard.postcontext.domain.Post;
import co.lilpilot.postcard.postcontext.domain.PostService;
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
        TagParam tagParam = new TagParam();
        tagParam.setCode("tag_code");
        tagParam.setName("tag_name");
        postCreateRequest.setTagParamList(Lists.newArrayList(tagParam));
        postCreateRequest.setContent("test_content");
        //when
        postAppService.createPost(postCreateRequest);
        //then
        then(postService).should().createPost(postArgumentCaptor.capture());
        Post post = postArgumentCaptor.getValue();
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getTagList().size()).isEqualTo(1);
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
