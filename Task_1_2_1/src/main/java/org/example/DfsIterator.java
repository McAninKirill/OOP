package org.example;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;
public class DfsIterator<T> implements Iterator<T>{
    private Tree<T> tree;
    private Stack<Tree<T>> stack;
    int amount;

    public DfsIterator(Tree<T> data){
        this.stack = new Stack<>();
        this.stack.push(data);
        this.tree = data;
        this.amount = data.getAmount();
    }


    @Override
    public boolean hasNext() {
        if (this.amount != this.tree.getAmount()) {
            throw new ConcurrentModificationException();
        }
        return !this.stack.empty();
    }


    public T next(){
        if(hasNext()){
            Tree<T> next = this.stack.pop();
            this.stack.addAll(next.getChildren());
            return next.getData();
        }
        return null;
    }
}
