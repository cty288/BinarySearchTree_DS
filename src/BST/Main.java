package BST;

public class Main {
    public static void main(String[] args) {
        /*
        Tree tree=new Tree();
        tree.insert(3);
        tree.insert(5);
        tree.insert(2);
        tree.recursiveInsert(10);
        tree.recursiveInsert(1);
        tree.recursiveInsert(8);
        tree.insert(4);
        tree.insert(15);
        tree.insert(16);
        tree.insert(7);
        tree.insert(18);
        tree.insert(11);
        tree.insert(12);
        tree.displayTree();

        //tree.delete(10);
        //tree.displayTree();

        //tree.preOrder();
        //tree.inOrder();
        //tree.postOrder();*/

        int[] arr=new int[]{4,10,5,0,3};
        //Tree t2= Tree.createBST(arr);
        //t2.displayTree();
        Tree.treeSort(arr);
    }
}
