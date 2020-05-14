package co.lilpilot.postcard.postcontext.application.message;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private int page;
    private int pageSize;
    private int total;
    private List<T> data;

    private Page(int page, int pageSize, int total, List<T> data) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }

    public static <T> Page<T> of(int page, int pageSize, int total, List<T> data) {
        return new Page<>(page, pageSize, total, data);
    }
}
