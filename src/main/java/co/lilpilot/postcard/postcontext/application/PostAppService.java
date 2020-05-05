package co.lilpilot.postcard.postcontext.application;

import co.lilpilot.postcard.postcontext.application.message.PostCreateRequest;
import co.lilpilot.postcard.postcontext.application.message.PostRequestAssembler;
import co.lilpilot.postcard.postcontext.domain.Post;
import co.lilpilot.postcard.postcontext.domain.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostAppService {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRequestAssembler postRequestAssembler;


    public List<Post> listLatest5() {
        return postService.queryPublicByPage(1, 5).getContent();
    }

    public Page<Post> queryByPage(Integer page, Integer pageSize) {
        return postService.queryByPage(page, pageSize);
    }

    @Transactional
    public void createPost(PostCreateRequest request) {
        Post post = postRequestAssembler.toPost(request);
        postService.createPost(post);
    }

    @Transactional
    public void removeTag(String tagCode) {
        postService.removeTag(tagCode);
    }
}
