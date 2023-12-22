package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        try {
            tree.addSubtree(subtree);
        } catch (NullException e) {
            System.out.println("Error: " + e.getMessage());
        }
        b.removeSubtree();
        tree.treeStream().forEach(System.out::println);
    }
}
