package co.lilpilot.postcard.postcontext.application;

import co.lilpilot.postcard.BaseMockitoTest;
import co.lilpilot.postcard.postcontext.domain.PostService;
import org.junit.jupiter.api.Test;
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
