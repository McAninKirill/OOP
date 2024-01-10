package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TreeTest {

    @Test
    public void toStringTest() throws NullException{
        Tree<Integer> tree = new Tree<>(1);
        Tree<Integer> subtree1 = new Tree<>(2);
        Tree<Integer> subtree2 = new Tree<>(7);

        subtree1.addChild(4);
        subtree2.addChild(8);
        subtree1.addSubtree(subtree2);

        tree.addSubtree(subtree1);
        tree.addChild(6);
        assertEquals(tree.toString(), "1: 2: [4: , 7: 8: [], ], 6: [], ");
    }
    @Test
    public void removeSubtreeTest(){
        Tree<Integer> tree = new Tree<>(1);
        Tree<Integer> subtree1 = new Tree<>(2,tree);


        subtree1.addChild(4);
        subtree1.addChild(5);
        tree.addChild(6);

        subtree1.removeSubtree();

        StringBuilder res = new StringBuilder();
        var dfs = tree.DfsIterator();
        while(dfs.hasNext()){
            res.append(dfs.next());
        }

        assertEquals("16", res.toString());
    }

    @Test
    public void removeTest(){
        Tree<Integer> tree = new Tree<>(1);
        Tree<Integer> subtree1 = new Tree<>(2,tree);


        subtree1.addChild(4);
        subtree1.addChild(5);
        tree.addChild(6);

        subtree1.removeElem();

        StringBuilder res = new StringBuilder();
        var dfs = tree.DfsIterator();
        while(dfs.hasNext()){
            res.append(dfs.next());
        }

        assertEquals("1546", res.toString());
    }

    @Test
    public void addChildTest(){
        Tree<Integer> tree = new Tree<>(1);


        tree.addChild(4);
        tree.addChild(5);
        tree.addChild(6);

        StringBuilder res = new StringBuilder();
        var dfs = tree.DfsIterator();
        while(dfs.hasNext()){
            res.append(dfs.next());
        }

        assertEquals("1654", res.toString());
    }

    @Test
    public void DFSTest(){
        Tree<Integer> tree = new Tree<>(1);


        tree.addChild(4);
        tree.addChild(5);
        tree.addChild(6);

        StringBuilder res = new StringBuilder();
        var dfs = tree.DfsIterator();
        while(dfs.hasNext()){
            res.append(dfs.next());
        }

        assertEquals("1654", res.toString());
    }

    @Test
    public void BFSTest(){
        Tree<Integer> tree = new Tree<>(1);


        tree.addChild(4);
        tree.addChild(5);
        tree.addChild(6);

        StringBuilder res = new StringBuilder();
        var dfs = tree.BfsIterator();
        while(dfs.hasNext()){
            res.append(dfs.next());
        }

        assertEquals("1456", res.toString());
    }

    @Test
    public void equalsTrueTest(){
        Tree<Integer> tree1 = new Tree<>(1);

        tree1.addChild(2);
        tree1.addChild(3);
        tree1.addChild(4);

        Tree<Integer> tree2 = new Tree<>(1);

        tree2.addChild(2);
        tree2.addChild(3);
        tree2.addChild(4);

        assertEquals(tree1.equals(tree2), true);
    }

    @Test
    public void equalsFalseTest(){
        Tree<Integer> tree1 = new Tree<>(1);

        tree1.addChild(2);
        tree1.addChild(3);
        tree1.addChild(4);

        Tree<Integer> tree2 = new Tree<>(1);

        tree2.addChild(3);
        tree2.addChild(2);
        tree2.addChild(4);

        assertEquals(tree1.equals(tree2), false);
    }
}
