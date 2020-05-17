package co.lilpilot.postcard.tagconfigcontext.gateway.controller;

import co.lilpilot.postcard.tagconfigcontext.application.TagConfigAppService;
import co.lilpilot.postcard.tagconfigcontext.application.message.TagConfigCreateRequest;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfig;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagConfigController {

    @Autowired
    private TagConfigAppService tagConfigAppService;

    @GetMapping("/config/tags")
    public List<TagConfig> listAll() {
        return tagConfigAppService.listAll();
    }

    @PostMapping("/config/tags")
    @ApiOperation("新增标签配置")
    public void addConfig(@RequestBody TagConfigCreateRequest request) {
        tagConfigAppService.addTagConfig(request);
    }

    @DeleteMapping("/config/tags/{tagConfigId}")
    @ApiOperation("删除标签配置")
    public void deleteConfig(@PathVariable Long tagConfigId) {
        tagConfigAppService.deleteTagConfig(tagConfigId);
    }
}
