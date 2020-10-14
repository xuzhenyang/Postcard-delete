package co.lilpilot.postcard.postcontext.application;

import co.lilpilot.postcard.postcontext.application.message.*;
import co.lilpilot.postcard.postcontext.domain.Post;
import co.lilpilot.postcard.postcontext.domain.PostService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostAppService {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRequestAssembler postRequestAssembler;


    public List<PostResponse> listLatest5() {
        List<Post> postList = postService.queryPublicByPage(1, 5).getContent();
        return postListToResponse(postList);
    }

    private List<PostResponse> postListToResponse(List<Post> postList) {
        return CollectionUtils.isEmpty(postList) ? Lists.newArrayList() :
                postList.stream().map(PostResponse::of).collect(Collectors.toList());
    }

    public Page<PostResponse> queryByPage(Integer page, Integer pageSize) {
        org.springframework.data.domain.Page<Post> result = postService.queryByPage(page, pageSize);
        List<Post> postList = result.getContent();
        return Page.of(result.getNumber() + 1, result.getSize(), result.getNumberOfElements(), postListToResponse(postList));
    }

    public PostResponse getById(Long id) {
        return PostResponse.of(postService.getById(id));
    }

    @Transactional
    public void createPost(PostCreateRequest request) {
        Post post = postRequestAssembler.toPost(request);
        postService.createPost(post);
    }

    @Transactional
    public void editPost(PostUpdateRequest request) {
        postService.editPost(request.getId(), request.getTitle(), request.getTagCodeList(), request.getContent());
    }

    @Transactional
    public void publishPost(Long id) {
        postService.publishPost(id);
    }

    @Transactional
    public void withdrawPost(Long id) {
        postService.withdrawPost(id);
    }

    @Transactional
    public void removeTag(String tagCode) {
        postService.removeTag(tagCode);
    }
}
