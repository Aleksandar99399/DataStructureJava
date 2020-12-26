package core;

import interfaces.Buffer;
import interfaces.Entity;
import model.BaseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Loader implements Buffer {


    private List<Entity> data;

    public Loader() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(Entity entity) {
        this.data.add(entity);
    }

    @Override
    public Entity extract(int id) {

        for (Entity entity : data) {
            if (entity.getId() == id) {
                this.data.remove(entity);
                return entity;
            }
        }

        return null;
    }

    @Override
    public Entity find(Entity entity) {

        if (data.contains(entity)) {
            return entity;
        }
        return null;
    }

    @Override
    public boolean contains(Entity entity) {
        return this.data.contains(entity);
    }

    @Override
    public int entitiesCount() {
        return this.data.size();
    }

    @Override
    public void replace(Entity oldEntity, Entity newEntity) {

        for (Entity datum : this.data) {
            if (datum == oldEntity) {
                int indexOf = this.data.indexOf(datum);
                this.data.set(indexOf, newEntity);
                return;
            }
        }
        throw new IllegalStateException(("Entity not found"));
    }

    @Override
    public void swap(Entity first, Entity second) {

        try {
            int indexOfFirst = first.getId();
            int indexOfSecond = second.getId();
            this.data.set(indexOfFirst, second);
            this.data.set(indexOfSecond, first);
        }catch (IllegalStateException ise){
            throw new IllegalStateException("Entities not found");
        }

    }

    @Override
    public void clear() {
        if (!this.data.isEmpty()) {
            this.data.clear();
        }
    }

    @Override
    public Entity[] toArray() {
        Entity[] arr = new Entity[0];
        if (this.data.isEmpty()) {
            return arr;
        } else {
            return this.data.toArray(new Entity[0]);
        }

    }

    @Override
    public List<Entity> retainAllFromTo(BaseEntity.Status lowerBound, BaseEntity.Status upperBound) {
        List<Entity> list = new ArrayList<>();

        for (int i = 0; i < this.data.size(); i++) {
            for (int j = lowerBound.ordinal(); j <= upperBound.ordinal(); j++) {
                if (this.data.get(i).getStatus().ordinal() == j) {
                    list.add(this.data.get(i));
                }

            }
        }

        return list;
    }

    @Override
    public List<Entity> getAll() {
        List<Entity> list = new ArrayList<>();
        if (this.data.isEmpty()) {
            return list;
        }
        return this.data;
    }

    @Override
    public void updateAll(BaseEntity.Status oldStatus, BaseEntity.Status newStatus) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getStatus() == oldStatus) {
                this.data.get(i).setStatus(newStatus);
            }
        }
    }

    @Override
    public void removeSold() {
        this.data.removeIf(datum -> datum.getStatus() == BaseEntity.Status.SOLD);
    }

    @Override
    public Iterator<Entity> iterator() {
        return new Iterator<>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < data.size();
            }

            @Override
            public Entity next() {
                return data.get(this.index++);
            }
        };
    }
}
