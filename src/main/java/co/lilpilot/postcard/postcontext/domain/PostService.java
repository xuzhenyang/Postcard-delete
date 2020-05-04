package co.lilpilot.postcard.postcontext.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public void removeTag(String tagCode) {
        if (StringUtils.isEmpty(tagCode)) {
            throw new RuntimeException("参数不能为空");
        }
        List<Post> postList = postRepository.findAll();
        if (CollectionUtils.isEmpty(postList)) {
            return;
        }
        for (Post post : postList) {
            post.removeTag(tagCode);
        }
    }

}
