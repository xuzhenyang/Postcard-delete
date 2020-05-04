package co.lilpilot.postcard.postcontext.application.message;

import lombok.Data;

import java.util.List;

@Data
public class PostCreateRequest {
    private String title;
    private List<String> tagCodeList;
    private String content;
}
