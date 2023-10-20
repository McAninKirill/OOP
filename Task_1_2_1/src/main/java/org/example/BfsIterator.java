package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
public class BfsIterator<T> implements Iterator<T>{
    private Tree<T> tree;
    private Queue<Tree<T>> queue;
    int amount;

    public BfsIterator(Tree<T> data) {
        this.queue = new LinkedList<>();
        this.queue.add(data);
        this.amount = data.getAmount();
        this.tree = data;
    }

    @Override
    public boolean hasNext() {
        if (this.amount != this.tree.getAmount()) {
            throw new ConcurrentModificationException();
        }
        return !this.queue.isEmpty();
    }

    public T next(){
        if (hasNext()) {
            Tree<T> next = this.queue.remove();
            this.queue.addAll(next.getChildren());
            return next.getData();
        }
        return null;
    }
}
