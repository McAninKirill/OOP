package org.example;

import java.util.ArrayList;
import java.util.Iterator;

public class Tree<T> implements Iterable<T>{
    private T data;
    private int amount;
    private Tree<T> parent;
    private ArrayList <Tree<T>> children;
    public Tree(T data){
        this.data = data;
        this.amount = 1;
        this.parent = null;
        this.children = new ArrayList<>();

    }

    public Tree(T data, Tree<T> parent){
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();

    }

    private void increaseAmount(){
        this.amount ++;
        if(this.parent != null){
            this.parent.increaseAmount();
        }
    }

    private void decreaseAmount(){
        this.amount--;
        if(this.parent != null){
            this.parent.decreaseAmount();
        }
    }

    public Tree<T> addChild(Tree<T> subtree){
        this.children.add(subtree);
        int cnt = subtree.getAmount();
        for(int i = 0; i < cnt; i++){
            increaseAmount();
        }
        return subtree;
    }
    public Tree<T> addChild(T child){
        Tree<T> new_elem = new Tree<>(child);
        this.children.add(new_elem);

        increaseAmount();
        return new_elem;
    }

    public int getAmount(){
        return this.amount;
    }
    public T getData(){
        return this.data;
    }

    public ArrayList<Tree<T>> getChildren() {
        return (ArrayList<Tree<T>>) children.clone();
    }

    public Iterator<T> BfsIterator(){
        return new BfsIterator<T>(this);
    }

    public Iterator<T> DfsIterator(){
        return new DfsIterator<T>(this);
    }

    public void removeSubtree(){
        if(this.parent != null){
            this.parent.children.remove(this);
            this.parent.decreaseAmount();
        }
    }

    public void remove(){
        if(this.parent != null){
            this.parent.children.addAll(this.children);
            this.parent.children.remove(this);
            this.parent.decreaseAmount();
        }
    }

    public boolean equals(Tree<T> tree) {
        ArrayList<T> list1 = new ArrayList<>();
        ArrayList<T> list2 = new ArrayList<>();

        var dfs1 = this.DfsIterator();
        var dfs2 = tree.DfsIterator();

        while(dfs1.hasNext()){
            list1.add(dfs1.next());
        }
        while(dfs2.hasNext()){
            list2.add(dfs2.next());
        }

        return (list1.equals(list2));
    }

    @Override
    public Iterator<T> iterator() {
        return new BfsIterator<>(this);
    }
}
