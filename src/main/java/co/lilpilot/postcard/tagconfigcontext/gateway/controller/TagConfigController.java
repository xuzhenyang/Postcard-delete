package co.lilpilot.postcard.tagconfigcontext.gateway.controller;

import co.lilpilot.postcard.tagconfigcontext.application.TagConfigAppService;
import co.lilpilot.postcard.tagconfigcontext.domain.TagConfig;
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
}
