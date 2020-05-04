package co.lilpilot.postcard.postcontext.application;

import co.lilpilot.postcard.postcontext.application.message.PostCreateRequest;
import co.lilpilot.postcard.postcontext.application.message.PostRequestAssembler;
import co.lilpilot.postcard.postcontext.domain.Post;
import co.lilpilot.postcard.postcontext.domain.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostAppService {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRequestAssembler postRequestAssembler;

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
