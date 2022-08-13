package com.destrostudios.entityworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EntityWorld extends ObservedComponentMap {

    private int nextEntity;
    private ConcurrentHashMap<Object, EntityObserver> systemEntityObserverMap = new ConcurrentHashMap<>();

    public Integer createEntity() {
        return nextEntity++;
    }
    
    public void removeEntity(Integer entity) {
        clearComponents(entity);
    }
    
    public EntityWrapper getWrapped(Integer entity) {
        return new EntityWrapper(this, entity);
    }
    
    public List<EntityWrapper> getWrapped(Collection<Integer> entities) {
        ArrayList<EntityWrapper> list = new ArrayList<>();
        for (Integer entity: entities) {
            list.add(new EntityWrapper(this, entity));
        }
        return list;
    }

    public EntityObserver getEntityObserver(Object key) {
        return systemEntityObserverMap.get(key);
    }

    public void createEntityObserver(Object key) {
        EntityObserver observer = new EntityObserver(this);
        systemEntityObserverMap.put(key, observer);
        observer.reset();
    }

    public EntityObserver getOrCreateEntityObserver(Object key) {
        if (getEntityObserver(key) == null) {
            createEntityObserver(key);
        }
        return getEntityObserver(key);
    }
}
