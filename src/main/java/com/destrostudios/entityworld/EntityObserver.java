package com.destrostudios.entityworld;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityObserver {

    private IntersectionTracker<Integer> tracker = new IntersectionTracker<>();
    private EntityWorld world;

    EntityObserver(EntityWorld world) {
        this.world = world;
    }

    public Set<Integer> NewEntities() {
        return tracker.getEntries();
    }

    public Set<Integer> RemovedEntities() {
        return tracker.getLeavers();
    }

    public void reset() {
        next(world.getEntitiesWithAll());
    }

    private void next(List<Integer> next) {
        HashSet<Integer> set = new HashSet<>(next);
        next(set);
    }

    public void next(Set<Integer> next) {
        tracker.next(next);
    }
}
