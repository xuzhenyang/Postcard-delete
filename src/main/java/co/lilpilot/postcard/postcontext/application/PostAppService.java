package co.lilpilot.postcard.postcontext.application;

import co.lilpilot.postcard.postcontext.domain.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostAppService {

    @Autowired
    private PostService postService;

    @Transactional
    public void removeTag(String tagCode) {
        postService.removeTag(tagCode);
    }
}
