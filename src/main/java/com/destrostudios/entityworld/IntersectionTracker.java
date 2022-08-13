package com.destrostudios.entityworld;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class IntersectionTracker<Element> {

    private Set<Element> last = new HashSet<>();
    private Set<Element> current = new HashSet<>();

    public void next() {
        next(new HashSet<>());
    }

    public void next(Collection<Element> nextGroup) {
        last = current;
        current = new HashSet<>(nextGroup);
    }

    public void add(Element element) {
        current.add(element);
    }

    public void addAll(Collection<Element> elements) {
        current.addAll(elements);
    }

    public Set<Element> getLeavers() {
        HashSet<Element> leavers = new HashSet<>(last);
        leavers.removeAll(current);
        return leavers;
    }

    public Set<Element> getEntries() {
        HashSet<Element> entries = new HashSet<>(current);
        entries.removeAll(last);
        return entries;
    }

    public Set<Element> getRepeaters() {
        HashSet<Element> repeaters = new HashSet<>(current);
        repeaters.retainAll(last);
        return repeaters;
    }

    public Set<Element> getAll() {
        return new HashSet<>(current);
    }
}
