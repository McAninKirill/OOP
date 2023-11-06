package org.example;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    public Tree<T> addChild(Tree<T> subtree) throws NullException{
        if (subtree == null) {
            throw new NullException("Can't add null subtree");
        }
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        var tree = (Tree<?>) obj;

        if (this.amount != (tree.amount)) {
            return false;
        }

        boolean result = true;

        ArrayList<T> list1 = new ArrayList<>();
        ArrayList<T> list2 = new ArrayList<>();

        var dfs1 = this.DfsIterator();
        var dfs2 = tree.DfsIterator();

        while (dfs1.hasNext() && dfs2.hasNext()) {
            if (!dfs1.next().equals(dfs2.next())) {
                return false;
            }
        }
        if (dfs1.hasNext() != dfs2.hasNext()) {
            result = false;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new BfsIterator<>(this);
    }

    public Stream<T> treeStream() {
        var mySpliterator = Spliterators.spliterator(this.iterator(), this.amount,
                Spliterator.IMMUTABLE | Spliterator.SIZED | Spliterator.DISTINCT
        );
        return StreamSupport.stream(mySpliterator, false);
    }
}
