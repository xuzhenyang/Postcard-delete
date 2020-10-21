package co.lilpilot.postcard.tagconfigcontext.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagConfigEditEvent {
    private final String tagCode;
    private final String newName;
}
