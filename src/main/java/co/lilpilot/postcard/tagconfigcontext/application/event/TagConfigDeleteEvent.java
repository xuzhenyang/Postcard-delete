package co.lilpilot.postcard.tagconfigcontext.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagConfigDeleteEvent {
    private final String tagCode;
}
