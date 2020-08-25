package top.mrl.server.servlet02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
    private List<Mapping> mappings;
    private List<Entity> entities;

    private Map<String, String> mappingMap = new HashMap<>();
    private Map<String, String> entityMap = new HashMap<>();

    public WebContext(List<Mapping> mappings, List<Entity> entities) {
        this.mappings = mappings;
        this.entities = entities;

        for (Mapping mapping:mappings) {
            for (String pattern:mapping.getPatterns()) {
                mappingMap.put(pattern, mapping.getName());
            }
        }
        for (Entity entity:entities) {
            entityMap.put(entity.getName(), entity.getCls());
        }
    }

    public String getClass(String pattern) {
        return entityMap.get(mappingMap.get(pattern));
    }
}
