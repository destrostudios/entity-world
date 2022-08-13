package com.destrostudios.entityworld;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class SimpleComponentMap implements EntityComponentMap, EntityComponentMapReadonly {

    private ConcurrentHashMap<Class<?>, ConcurrentHashMap<Integer, Object>> componentMaps = new ConcurrentHashMap<>();
    private Comparator<Class<?>> mapSizeComparator = Comparator.comparingInt(o -> getComponentMap(o).size());

    public boolean isEmpty() {
        for (Class<?> key : componentMaps.keySet()) {
            if (!componentMaps.get(key).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private ConcurrentHashMap<Integer, Object> getComponentMap(Class<?> componentClass) {
        ConcurrentHashMap<Integer, Object> componentMap = componentMaps.get(componentClass);
        if (componentMap == null) {
            componentMap = new ConcurrentHashMap<>();
            componentMaps.put(componentClass, componentMap);
        }
        return componentMap;
    }

    protected ConcurrentHashMap<Class<?>, ConcurrentHashMap<Integer, Object>> getComponentMaps() {
        return componentMaps;
    }

    public <T> T getComponent(Integer entity, Class<T> componentClass) {
        return (T) getComponentMap(componentClass).get(entity);
    }

    public boolean hasComponent(Integer entity, Class<?> componentClass) {
        return getComponentMap(componentClass).get(entity) != null;
    }

    public Object setComponent(Integer entity, Object component) {
        return getComponentMap(component.getClass()).put(entity, component);
    }

    public Object removeComponent(Integer entity, Class<?> componentClass) {
        return getComponentMap(componentClass).remove(entity);
    }

    public Set<Object> getComponents(Integer entity) {
        HashSet<Object> components = new HashSet<>();
        Object component;
        for (ConcurrentHashMap<Integer, Object> componentMap: componentMaps.values()) {
            component = componentMap.get(entity);
            if(component != null)
            {
                components.add(component);
            }
        }
        return components;
    }

    public void clearComponents(Integer entity) {
        for(Object component: getComponents(entity)) {
            removeComponent(entity, component.getClass());
        }
    }

    public boolean hasAllComponents(Integer entity, Class<?>... componentsClasses) {
        for (Class<?> componentClass: componentsClasses) {
            if (!getComponentMap(componentClass).containsKey(entity)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasAnyComponent(Integer entity, Class<?>... componentsClasses) {
        for (Class<?> componentClass: componentsClasses) {
            if (getComponentMap(componentClass).containsKey(entity)) {
                return true;
            }
        }
        return false;
    }

    public Set<Integer> getEntitiesWithAll(Class<?>... componentsClasses) {
        HashSet<Integer> entitySet = new HashSet<>();
        if (componentsClasses.length == 0) {
            for (ConcurrentHashMap<Integer, Object> map: componentMaps.values()) {
                entitySet.addAll(map.keySet());
            }
            return entitySet;
        }

        Arrays.sort(componentsClasses, mapSizeComparator);

        for (Integer entity: getComponentMap(componentsClasses[0]).keySet()) {
            if (hasAllComponents(entity, componentsClasses)) {
                entitySet.add(entity);
            }
        }
        return entitySet;
    }

    public Set<Integer> getEntitiesWithAny(Class<?>... componentsClasses) {
        HashSet<Integer> entitySet = new HashSet<>();
        for (Class<?> componentClass: componentsClasses) {
            entitySet.addAll(getComponentMap(componentClass).keySet());
        }
        return entitySet;
    }

    public void clear() {
        for (ConcurrentHashMap<Integer, Object> map: componentMaps.values()) {
            map.clear();
        }
    }

    public boolean hasEntity(Integer entity) {
        for (ConcurrentHashMap<Integer, Object> map : componentMaps.values()) {
            if (map.containsKey(entity)) {
                return true;
            }
        }
        return false;
    }
}
