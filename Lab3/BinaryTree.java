import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTree {
    Node root;

    // Preorder traversal
    public void preorderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // Inorder traversal
    public void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    // Postorder traversal
    public void postorderTraversal(Node node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

      
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the value of the root node:");
            int rootValue = scanner.nextInt();
            tree.root = new Node(rootValue);

            HashSet<Integer> uniqueValues = new HashSet<>();
            uniqueValues.add(rootValue);

            Queue<Node> queue = new LinkedList<>();
            queue.add(tree.root);

            while (!queue.isEmpty()) {
                Node currentNode = queue.poll();

                try {
                    System.out.println("Enter left child value of " + currentNode.data + " (or -1 to skip):");
                    int leftValue = scanner.nextInt();
                    if (leftValue != -1) {
                        if (uniqueValues.contains(leftValue)) {
                            System.out.println("Error: Duplicate value entered. Please enter a unique value.");
                            System.exit(0);
                        }
                        currentNode.left = new Node(leftValue);
                        uniqueValues.add(leftValue);
                        queue.add(currentNode.left);
                    }

                    System.out.println("Enter right child value of " + currentNode.data + " (or -1 to skip):");
                    int rightValue = scanner.nextInt();
                    if (rightValue != -1) {
                        if (uniqueValues.contains(rightValue)) {
                            System.out.println("Error: Duplicate value entered. Please enter a unique value.");
                            continue;
                        }
                        currentNode.right = new Node(rightValue);
                        uniqueValues.add(rightValue);
                        queue.add(currentNode.right);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid integer value.");
                    System.exit(0);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter a valid integer value for the root node.");
        } finally {
            scanner.close();
        }

        // Perform traversals
        System.out.print("Preorder Traversal: ");
        tree.preorderTraversal(tree.root);
        System.out.println();

        System.out.print("Inorder Traversal: ");
        tree.inorderTraversal(tree.root);
        System.out.println();

        System.out.print("Postorder Traversal: ");
        tree.postorderTraversal(tree.root);
        System.out.println();
    }
}

