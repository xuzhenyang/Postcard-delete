package co.lilpilot.postcard.postcontext.application.message;

import lombok.Data;

import java.util.List;

@Data
public class PostUpdateRequest {
    private Long id;
    private String title;
    private List<TagParam> tagParamList;
    private String content;
}
