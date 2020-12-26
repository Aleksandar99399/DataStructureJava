package core;

import interfaces.Entity;
import interfaces.Repository;


import java.util.*;

public class Data implements Repository {

    private Entity root;
    private Queue<Entity> data;

    public Data(){
        this.data=new PriorityQueue<>();
    }
    public Data(Data other){
        this.data=new PriorityQueue<>();
        this.data=new PriorityQueue<>(other.data);
    }

    @Override
    public void add(Entity entity) {
        if (this.root==null){
            this.root=entity;
        }else {
            this.getById(entity.getParentId()).addChild(entity);
        }
        this.data.offer(entity);

    }

    @Override
    public Entity getById(int id) {

        for (Entity datum : this.data) {
            if (datum.getId()==id){
                return datum;
            }
        }
        return null;
    }

    @Override
    public List<Entity> getByParentId(int id) {
        List<Entity>entities=new ArrayList<>();
        for (Entity datum : this.data) {
            if (datum.getParentId()==id){
                entities.add(datum);
            }
        }

        return entities;
    }

    @Override
    public List<Entity> getAll() {
        List<Entity>list= new ArrayList<>(data);
        return list;
    }


    @Override
    public Repository copy() {
        return  new Data(this);

    }

    //private void heapiFyDown(Repository repository){
    //    while (!this.root.getChildren().isEmpty()){
    //
    //    }
    //}

    @Override
    public List<Entity> getAllByType(String type) {
        List<Entity>list=new ArrayList<>();
        if (!type.equals("Invoice")&&!type.equals("StoreClient")&&!type.equals("User")){
            throw new IllegalArgumentException ("Illegal type: " + type);
        }
        for (Entity datum : this.data) {
            if (datum.getClass().getSimpleName().equals(type)){
                list.add(datum);
            }
        }
        return list;
        //return this.data.stream().filter(e->e.getClass().getSimpleName().equals(type)).collect(Collectors.toList());



    }

    @Override
    public Entity peekMostRecent() {
        if(this.data.size()==0){
            throw new IllegalStateException("Operation on empty Data");
        }
        Entity peek = this.data.peek();
        return peek;

    }

    @Override
    public Entity pollMostRecent() {
        if(this.data.size()==0){
            throw new IllegalStateException("Operation on empty Data");
        }
        Entity result = this.data.poll();

        this.getById(result.getParentId()).getChildren().remove(result);
        return result;

    }

    @Override
    public int size() {
        return this.data.size();
    }
}
