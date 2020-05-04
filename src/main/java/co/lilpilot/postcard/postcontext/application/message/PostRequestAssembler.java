package co.lilpilot.postcard.postcontext.application.message;

import co.lilpilot.postcard.postcontext.domain.Post;
import co.lilpilot.postcard.postcontext.domain.Tag;
import co.lilpilot.postcard.postcontext.interfaces.client.TagConfigClient;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class PostRequestAssembler {
    @Autowired
    private TagConfigClient tagConfigClient;

    public Post toPost(PostCreateRequest request) {
        String title = request.getTitle();
        String content = request.getContent();
        List<Tag> tagList = null;
        List<String> tagCodeList = request.getTagCodeList();
        if (!CollectionUtils.isEmpty(tagCodeList)) {
            tagList = Lists.newArrayList();
            for (String tagCode : tagCodeList) {
                tagList.add(tagConfigClient.getTagByCode(tagCode));
            }
        }
        return new Post(title, tagList, content);
    }
}
