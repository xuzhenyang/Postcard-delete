package co.lilpilot.postcard.postcontext.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostEntityTest {

    @Test
    void testCreate() {
        PostEntity postEntity = new PostEntity("测试标题", "测试内容");
        assertThat(postEntity.getTitle()).isEqualTo("测试标题");
        assertThat(postEntity.getContent()).isEqualTo("测试内容");
    }

    @Test
    void testCreateWithNullOrEmpty() {
        assertThatThrownBy(() -> new PostEntity(null, "测试内容"))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> new PostEntity("", "测试内容"))
            .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> new PostEntity("测试标题", null))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> new PostEntity("测试标题", ""))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testModify() {
        PostEntity postEntity = new PostEntity("测试标题", "测试内容");
        postEntity.modify("测试标题2", "测试内容2");
        assertThat(postEntity.getTitle()).isEqualTo("测试标题2");
        assertThat(postEntity.getContent()).isEqualTo("测试内容2");
    }
    
    @Test
    void testModifyWithNullOrEmpty() {
        PostEntity postEntity = new PostEntity("测试标题", "测试内容");
        assertThatThrownBy(() -> postEntity.modify(null, "测试内容"))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> postEntity.modify("", "测试内容"))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> postEntity.modify("测试标题", null))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> postEntity.modify("测试标题", ""))
                .isInstanceOf(RuntimeException.class);
    }
}