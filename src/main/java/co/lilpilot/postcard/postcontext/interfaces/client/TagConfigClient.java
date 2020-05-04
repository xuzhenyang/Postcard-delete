package co.lilpilot.postcard.postcontext.interfaces.client;

import co.lilpilot.postcard.postcontext.domain.Tag;

public interface TagConfigClient {
    Tag getTagByCode(String tagCode);
}
