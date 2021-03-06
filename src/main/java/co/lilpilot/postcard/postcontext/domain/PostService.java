package co.lilpilot.postcard.postcontext.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Page<Post> queryPublicByPage(Integer page, Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return postRepository.pagePublicPosts(pageRequest);
    }

    public Page<Post> queryByPage(Integer page, Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return postRepository.findAll(pageRequest);
    }

    public Post getById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post editPost(Long id, String title, List<Tag> tagList, String content) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (!postOptional.isPresent()) {
            throw new RuntimeException("文章不存在");
        }
        Post post = postOptional.get();
        post.edit(title, tagList, content);
        return postRepository.save(post);
    }

    public Post publishPost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (!postOptional.isPresent()) {
            throw new RuntimeException("文章不存在");
        }
        Post post = postOptional.get();
        post.publish();
        return postRepository.save(post);
    }

    public Post withdrawPost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (!postOptional.isPresent()) {
            throw new RuntimeException("文章不存在");
        }
        Post post = postOptional.get();
        post.withdraw();
        return postRepository.save(post);
    }

    public void editTag(String tagCode, String newTagName) {
        if (StringUtils.isEmpty(tagCode) || StringUtils.isEmpty(newTagName)) {
            throw new RuntimeException("参数不能为空");
        }
        List<Post> postList = postRepository.findAll();
        if (CollectionUtils.isEmpty(postList)) {
            return;
        }
        for (Post post : postList) {
            post.editTag(tagCode, newTagName);
            postRepository.save(post);
        }
    }

    //TODO refactor 区分文章删除标签和删除标签配置
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
