package BST;

public class Node {
    public int data;
    public Node leftChild;
    public Node rightChild;

    public void display(){
        System.out.println(data);
    }

    public Node(int data){
        this.data=data;
        this.leftChild=null;
        this.rightChild=null;
    }
}
