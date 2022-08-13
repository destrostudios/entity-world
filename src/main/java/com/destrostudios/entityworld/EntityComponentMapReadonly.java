package com.destrostudios.entityworld;

import java.util.Set;

public interface EntityComponentMapReadonly {
    <T> T getComponent(Integer entity, Class<T> componentClass);
    boolean hasComponent(Integer entity, Class<?> componentClass);
    boolean hasAllComponents(Integer entity, Class<?>... componentsClasses);
    boolean hasAnyComponent(Integer entity, Class<?>... componentsClasses);
    Set<Object> getComponents(Integer entity);
    Set<Integer> getEntitiesWithAll(Class<?>... componentsClasses);
    Set<Integer> getEntitiesWithAny(Class<?>... componentsClasses);
    boolean isEmpty();
    boolean hasEntity(Integer entity);
}
