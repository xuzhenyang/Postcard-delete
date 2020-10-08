package co.lilpilot.postcard.postcontext.gateway.controller;

import co.lilpilot.postcard.postcontext.application.PostAppService;
import co.lilpilot.postcard.postcontext.application.message.Page;
import co.lilpilot.postcard.postcontext.application.message.PostCreateRequest;
import co.lilpilot.postcard.postcontext.application.message.PostUpdateRequest;
import co.lilpilot.postcard.postcontext.domain.Post;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class PostManageController {

    @Autowired
    private PostAppService postAppService;

    @GetMapping("/posts")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("分页查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条目数", defaultValue = "10", dataType = "Integer", paramType = "query")
    })
    public Page<Post> pagePosts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        return postAppService.queryByPage(page, pageSize);
    }

    @GetMapping("/posts/{postId}")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("获取文章")
    public Post getById(@PathVariable Long postId) {
        return postAppService.getById(postId);
    }

    @PostMapping("/posts")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("创建文章")
    @ApiImplicitParam(name = "postCreateRequest", value = "创建文章参数", required = true, dataType = "PostCreateRequest")
    public void createPost(@RequestBody PostCreateRequest postCreateRequest) {
        postAppService.createPost(postCreateRequest);
    }

    @PutMapping("/posts/{postId}")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("更新文章")
    @ApiImplicitParam(name = "postUpdateRequest", value = "创建文章参数", required = true, dataType = "PostUpdateRequest")
    public void updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
        postUpdateRequest.setId(postId);
        postAppService.editPost(postUpdateRequest);
    }

    @PutMapping("/posts/{postId}/publish")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("发布文章")
    public void publishPost(@PathVariable Long postId) {
        postAppService.publishPost(postId);
    }

    @PutMapping("/posts/{postId}/withdraw")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("撤回文章")
    public void withdrawPost(@PathVariable Long postId) {
        postAppService.withdrawPost(postId);
    }

}
