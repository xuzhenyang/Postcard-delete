package co.lilpilot.postcard.tagconfigcontext.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class TagConfigService {

    @Autowired
    private TagConfigRepository tagConfigRepository;

    public TagConfig addTagConfig(String code, String name) {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(name)) {
            throw new RuntimeException("参数不能为空");
        }
        TagConfig exist = tagConfigRepository.findByCode(code);
        if (exist != null) {
            throw new RuntimeException("标签配置已存在");
        }
        exist = tagConfigRepository.findByName(name);
        if (exist != null) {
            throw new RuntimeException("标签配置已存在");
        }
        TagConfig tagConfig = new TagConfig(code, name);
        return tagConfigRepository.save(tagConfig);
    }

    public TagConfig delete(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("参数不能为空");
        }
        Optional<TagConfig> tagConfigOptional = tagConfigRepository.findById(id);
        if (!tagConfigOptional.isPresent()) {
            throw new RuntimeException("标签配置不存在");
        }
        tagConfigRepository.deleteById(id);
        return tagConfigOptional.get();
    }

    public TagConfig editName(Long tagId, String newName) {
        if (Objects.isNull(tagId) || StringUtils.isEmpty(newName)) {
            throw new RuntimeException("参数不能为空");
        }
        TagConfig existTag = tagConfigRepository.findByName(newName);
        if (Objects.nonNull(existTag)) {
            throw new RuntimeException("名称已存在");
        }
        Optional<TagConfig> tagConfigOptional = tagConfigRepository.findById(tagId);
        if (!tagConfigOptional.isPresent()) {
            throw new RuntimeException("标签配置不存在");
        }
        TagConfig tagConfig = tagConfigOptional.get();
        tagConfig.edit(newName);
        tagConfigRepository.save(tagConfig);
        return tagConfig;
    }
}
