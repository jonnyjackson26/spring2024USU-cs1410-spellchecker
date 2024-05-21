public class BinarySearchTree<E extends Comparable<E>> {
    private class TreeNode {
        E value;
        TreeNode left;
        TreeNode right;
        public TreeNode(E value) {
            this.value = value;
        }
    }
    private TreeNode root;
    public void display(String message) {
        System.out.println(message);
        displayInOrder(root);
    }
    public boolean remove(E value) {
        if(!search(value)) { //value not in tree
            return false;
        } else {
            TreeNode parent = null;
            TreeNode node = root;
            boolean done = false;
            while (!done) {
                if (node.value.compareTo(value) < 0) {
                    parent = node;
                    node = node.right;
                } else if (node.value.compareTo(value) > 0) {
                    parent = node;
                    node = node.left;
                } else {
                    done = true;
                }
            }
            // Case for no left child
            if (node.left == null) {
                if (parent == null) {
                    root = node.right;
                } else {
                    if (parent.value.compareTo(value) < 0) {
                        parent.right = node.right;
                    } else {
                        parent.left = node.right;
                    }
                }
            } else { // Case for left child
                TreeNode parentOfRight = node;
                TreeNode rightMost = node.left;
                while (rightMost.right != null) {
                    parentOfRight = rightMost;
                    rightMost = rightMost.right;
                }
                node.value = rightMost.value;
                if (parentOfRight.right == rightMost) {
                    parentOfRight.right = rightMost.left;
                } else {
                    parentOfRight.left = rightMost.left;
                }
            }
            return true;
        }
    }
    private void displayInOrder(TreeNode node) {
        if (node == null) return;
        displayInOrder(node.left);
        System.out.println(node.value);
        displayInOrder(node.right);
    }
    public boolean insert(E value) {
        if (root == null) {
            root = new TreeNode(value);
            return true;
        } else {
            if(search(value)) { //value already in tree
                return false;
            } else {
                TreeNode parent = null;
                TreeNode node = root;
                while (node != null) {
                    parent = node;
                    if (node.value.compareTo(value)<0) {
                        node = node.right;
                    } else {
                        node = node.left;
                    }
                }
                TreeNode newNode = new TreeNode(value);
                if (parent.value.compareTo(value)<0) {
                    parent.right = newNode;
                } else {
                    parent.left = newNode;
                }
                return true;
            }
        }
    }
    public boolean search(E value) {
        boolean found = false;
        TreeNode node = root;
        while (!found && node != null) {
            if (node.value.compareTo(value)==0) {
                found = true;
            } else if (node.value.compareTo(value)<0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return found;
    }

    public int numberNodes() {
        return numberNodes(root);
    }
    private int numberNodes(TreeNode node) {
        if(node!=null) {
            return 1+numberNodes(node.left)+numberNodes(node.right);
        } else {
            return 0;
        }
    }

    public int numberLeafNodes() {
        return numberLeafNodes(root);
    }
    private int numberLeafNodes(TreeNode node) {
        if(node!=null) {
            if(node.left==null && node.right==null ) {
                return 1;
            } else {
                return numberLeafNodes(node.left)+numberLeafNodes(node.right);
            }
        } else {
            return 0;
        }
    }
    public int height() {
        return height(root);
    }
    private int height(TreeNode node) {
        if(node==null) {
            return -1;
        } else {
            return 1 + Math.max(height(node.left), height(node.right));
//            int leftHeight=height(node.left);
//            int rightHeight=height(node.right);
//            if(leftHeight>rightHeight) {
//                return 1+leftHeight;
//            } else {
//                return 1+rightHeight;
//            }
            //return 1+Math.max(leftHeight,rightHeight);
        }
    }
}