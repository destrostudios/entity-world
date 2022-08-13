package com.destrostudios.entityworld;

public interface EntityComponentMap extends EntityComponentMapReadonly {
    Object setComponent(Integer entity, Object component);
    Object removeComponent(Integer entity, Class<?> componentClass);
    void clearComponents(Integer entity);
    void clear();
}
