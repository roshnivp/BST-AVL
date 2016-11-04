/**
 * 
 */
package project2;
import java.lang.Math;
/**
 * @author Roshni Velluva Puthanidam,Joshua Palamuttam
 * Jun 23, 201611:15:55 AM
 */
public class BinarySearchTree<T extends Comparable<T>>{
      BinaryNode<T> root;
      
      public BinarySearchTree()
      {
          root = null;
      }

      public boolean isEmpty()
      {
          return root == null;
      }

      public BinaryNode<T> getRoot()
      {
          return root;
      }
   
      //return height
      public int height() 
      {
          return height(root);
      }
      
    //return the height of the tree
      int height(BinaryNode<T> node){
          if(node==null){
              return -1;
          }
          else{
              return 1+Math.max(height(node.getLeft()),height(node.getRight()));
          }
      }
      
      
     //to search for an element in the tree
      public boolean contains(T data)
      {
          return contains(data, root);
      }
      
      //return whether the searched element is there or not
      boolean contains(T data, BinaryNode<T> root)
      {
          if(root == null)
          {
              return false;
          }

          int compareResult = data.compareTo(root.getData());

          if(compareResult < 0)
          {
              return contains(data, root.getLeft());
          }
          else if(compareResult > 0)
          {
              return contains(data, root.getRight());
          }
          else
          {
              return true;
          }
      }
      
      
      /* Given a non-empty binary search tree,
    	return the minimum data value found in that
    	tree. Note that the entire tree does not need
    	to be searched. */
      public T findMin() {
          BinaryNode<T> node = findMin(root);
          return node == null ? null : node.getData();
      }
      
      BinaryNode<T> findMin(BinaryNode<T> root) {   
    	  if(root == null) {
              return null;
          }
    	  else if(root.getLeft() == null) {
              return root;
          } 
    	  /* loop down to find the leftmost leaf */
    	  else {
              return findMin(root.getLeft());
          }
      }
      
      
//      return the maximum data value
      public T findMax() {
          BinaryNode<T> node = findMax(root);
          return node == null ? null : node.getData();
      }
      
      BinaryNode<T> findMax(BinaryNode<T>  root) {
	          if(root == null) {
	              return null;
	          } 
	    	  else if(root.getRight() == null) {
	              return root;
	          }
	    	  /* loop down to find the rightmost leaf */
	    	  else {
	              return findMin(root.getRight());
	          }
      }
      
      //insert element to the tree
      public void insert(T data) {
          root = insert(data, root);
      }

      protected BinaryNode<T> insert(T data, BinaryNode<T> root) {
          if(root == null) {
              return new BinaryNode<>(data);
          }

          int compareResult =data.compareTo(root.getData());//compare data to be inserted with root data,insert accordingly

          if(compareResult < 0) {
              root.setLeft(insert(data, root.getLeft()));
          } else if(compareResult > 0) {
              root.setRight(insert(data, root.getRight()));
          }

          return root;
      }
      
      
      //Remove each node from tree
      public void remove(T data) {
          root = remove(data, root);
      }

      protected BinaryNode<T> remove(T data, BinaryNode<T> root) {
          if(root == null) {
              return null;
          }

          int compareResult = data.compareTo(root.getData());

          if(compareResult < 0) {
              root.setLeft(remove(data, root.getLeft()));
          } else if(compareResult > 0) {
              root.setRight(remove(data, root.getRight()));
          } else if(root.getLeft() != null && root.getRight() != null) {
              root.setData(findMin(root.getRight()).getData());
              root.setRight(remove(root.getData(), root.getRight()));
          } else {
              root = root.getLeft() != null ? root.getLeft() : root.getRight();
          }

          return root;
      }
      
      
      //print tree
      public void printTree() {
          printTree(root);
      }

      protected void printTree(BinaryNode<T> root) {
          if(root != null) {
              printTree(root.getLeft());
              System.out.println(root.getData());
              printTree(root.getRight());
          }
      }

}
