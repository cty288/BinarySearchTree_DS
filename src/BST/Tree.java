package BST;

import java.util.Stack;

public class Tree {
    private Node root;

    public Tree(){
        root=null;
    }

    //search for a node, given its data
    //test push
    public Node search(int data){
        Node node=root;
        if(root==null){
            return null;
        }
        while(node!=null && node.data!=data){
            if(data> node.data){
                node=node.rightChild;
            }else{
                node=node.leftChild;
            }
        }
        if(node==null){
            return null;
        }
        return node;
    }

    public Node recursiveSearch(int data){
        return recursiveSearch(data,root);
    }

    private Node recursiveSearch(int data, Node node){
        if(node==null){
            return null;
        }
        if(data== node.data){
            return node;
        }
        if(data> node.data){
            return recursiveSearch(data,node.rightChild);
        }else {
            return recursiveSearch(data, node.leftChild);
        }
    }

    public void insert(int data){
        Node node=root;
        Node newNode=new Node(data);
        if(node==null){
            root=newNode;
        }else{
            int i=1;
            while(true){
                if(data<= node.data){
                    if(node.leftChild!=null){
                        node=node.leftChild;
                    }else{
                        node.leftChild=newNode;
                        break;
                    }
                }else{
                    if(node.rightChild!=null){
                        node=node.rightChild;
                    }else{
                        node.rightChild=newNode;
                        break;
                    }
                }
            }
        }
    }

    public void recursiveInsert(int data){
        recursiveInsert(data,root);
    }

    private void recursiveInsert(int data, Node root){
        Node newNode=new Node(data);
        if(root==null){
            root=newNode;
        }else{
            if(data<= root.data){
                if(root.leftChild!=null){
                    recursiveInsert(data,root.leftChild);
                }else{
                    root.leftChild=newNode;
                }
            }else{
                if(root.rightChild!=null){
                    recursiveInsert(data,root.rightChild);
                }else{
                    root.rightChild=newNode;
                }
            }
        }
    }

    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while(isRowEmpty==false)
        {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for(int j=0; j<nBlanks; j++)
                System.out.print(' ');

            while(globalStack.isEmpty()==false)
            {
                Node temp = (Node)globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.data);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if(temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<nBlanks*2-2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }  // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }  // end displayTree()


    public boolean delete(int key){
        Node node=root;
        Node parent=node;
        boolean isLeft=true;

        if(root==null){
            return false;
        }
        while(true){
            if(key> node.data){
                parent=node;
                node=node.rightChild;
                isLeft=false;
            }else if (key< node.data){
                parent=node;
                node =node.leftChild;
                isLeft=true;
            }else{
                break;
            }
            if(node==null){
                return false;
            }
        }
        //node is a leaf
        if(node.rightChild==null && node.leftChild==null){
            if(node==root){
                root=null;
            }else if(isLeft){
                parent.leftChild=null;
            }else{
                parent.rightChild=null;
            }
            node=null;
        }else if (node.leftChild==null && node.rightChild!=null){ //has a right child
            Node rightChild=node.rightChild;
            if(node==root){
                root=rightChild;
            }else if (isLeft){
                parent.leftChild=rightChild;
            }else{
                parent.rightChild=rightChild;
            }
            node=null;
        }else if (node.leftChild!=null && node.rightChild==null) { //has a left child
            Node leftChild=node.leftChild;
            if(node==root){
                root=leftChild;
            }else if (isLeft){
                parent.leftChild=leftChild;
            }else{
                parent.rightChild=leftChild;
            }
            node=null;
        }else{ //has two children
            Node rightChild=node.rightChild;
            Node successor=rightChild;
            Node successorParent=node;

            //find the minimum child (successor) of "rightchild"
            while(successor.leftChild!=null){
                successorParent=successor;
                successor=successor.leftChild;
            }

            if(successor!=rightChild){
                successorParent.leftChild=successor.rightChild;
                successor.rightChild=rightChild;
            }

            successor.leftChild=node.leftChild;

            if(node==root) {
                root=successor;
            }else if(isLeft){
                parent.leftChild=successor;
            }else{
                parent.rightChild=successor;
            }
        }
        return true;
    }

    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node root){
        if(root==null){
            return;
        }
        root.display();
        preOrder(root.leftChild);
        preOrder(root.rightChild);
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node root){
        if(root==null){
            return;
        }
        inOrder(root.leftChild);
        root.display();
        inOrder(root.rightChild);
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node root){
        if(root==null){
            return;
        }
        postOrder(root.leftChild);
        postOrder(root.rightChild);
        root.display();
    }

    public static Tree createBST(int[] arr){
        Tree newTree=new Tree();
        for(int i=0; i<arr.length; i++){
            newTree.insert(arr[i]);
        }
        return newTree;
    }

    public static void treeSort(int[] arr){
        Tree tree=createBST(arr);
        tree.inOrder();
    }

}
