/**
 * 
 */
package project2;

/**
 * @author Roshni Velluva Puthanidam,Joshua Palamuttam
 *Jun 24, 20165:22:08 PM
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>
{
    
	
    private boolean shouldNeedRotations;

    public AVLTree()
    {
        this(false);
    }

    public AVLTree(boolean shouldNeedRotations)
    {
        this.shouldNeedRotations = shouldNeedRotations;
    }

    
    public boolean isBalanced()
    {
        return isBalanced(root);
    }
    
    
    //check whether the tree is balanced after each insertion
    private boolean isBalanced(BinaryNode<T> root){
	    if(root==null){
	    	return true;
	    }
	    else{
	    	int leftHeight= height(root.getLeft());
	    	int rightHeight= height(root.getRight());
	    	return Math.abs(leftHeight-rightHeight) <= 1;
	    	
	    }
    }
    
    
    @Override //insertion,do the balancing if the tree is unbalanced due to the insertion
    protected BinaryNode<T> insert(T data, BinaryNode<T> root)
    {
        return balance(super.insert(data, root));
    }

    @Override //removal,do the balancing if the tree is unbalanced due to the insertion
    protected BinaryNode<T> remove(T data, BinaryNode<T> root)
    {
        return balance(super.remove(data, root));
    }
    @Override//search for an element
    protected boolean contains(T data, BinaryNode<T> root)
    {
        return (super.contains(data, root));
    }
    
    //balance the tree using proper rotation
    private BinaryNode<T> balance(BinaryNode<T> root)
    {
        if(root == null)
        {
            return null;
        }

        if(height(root.getLeft()) - height(root.getRight()) > 1)
        {
            if(height(root.getLeft().getLeft()) >= height(root.getLeft().getRight()))
            {
                root = singleRightRotation(root);
            }
            else
            {
                root = doubleLeftRightRotation(root);
            }
        }
        else if(height(root.getRight()) - height(root.getLeft()) > 1)
        {
            if(height(root.getRight().getRight()) >= height(root.getRight().getLeft()))
            {
                root = singleLeftRotation(root);
            }
            else
            {
                root = doubleRightLeftRotation(root);
            }
        }

        return root;
    }
    
    private BinaryNode<T> singleRightRotation(BinaryNode<T> k2)
    {
        if(shouldNeedRotations)
        {
            System.out.println("Single Right Rotation: " + k2.getData());
        }
        BinaryNode<T> k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
        return k1;
    }

    private BinaryNode<T> singleLeftRotation(BinaryNode<T> k1)
    {
        if(shouldNeedRotations)
        {
            System.out.println("Single Left Rotation: " + k1.getData());
        }
        BinaryNode<T> k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
        k2.setHeight(Math.max(height(k2.getRight()), k1.getHeight()) + 1);
        return k2;
    }

    private BinaryNode<T> doubleLeftRightRotation(BinaryNode<T> k3)
    {
        if(shouldNeedRotations)
        {
            System.out.println("Double Left-Right Rotation: " + k3.getData());
        }
        k3.setLeft(singleLeftRotation(k3.getLeft()));
        return singleRightRotation(k3);
    }

    private BinaryNode<T> doubleRightLeftRotation(BinaryNode<T> k1)
    {
        if(shouldNeedRotations)
        {
            System.out.println("Double Right-Left Rotation: " + k1.getData());
        }
        k1.setRight(singleRightRotation(k1.getRight()));
        return singleLeftRotation(k1);
    }
}