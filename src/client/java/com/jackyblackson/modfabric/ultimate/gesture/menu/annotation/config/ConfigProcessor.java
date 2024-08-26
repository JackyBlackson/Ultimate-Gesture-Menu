package com.jackyblackson.modfabric.ultimate.gesture.menu.annotation.config;

import com.jackyblackson.modfabric.ultimate.gesture.menu.UltimateGestureMenu;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ConfigProcessor {
    public static Map<String, Object> getConfigFields(Object obj) {
        Map<String, Object> configMap = new HashMap<>();

        // 获取类的所有字段
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            // 检查字段是否带有 @ConfigField 注解
            if (field.isAnnotationPresent(ConfigField.class)) {
                field.setAccessible(true);
                try {
                    // 获取字段名称和对应的值，并存储到 map 中
                    configMap.put(field.getName(), field.get(obj));
                } catch (IllegalAccessException e) {
                    UltimateGestureMenu.LOGGER.trace("Can't access to config files: ", e);
                }
            }
        }

        return configMap;
    }
}
