/**
 * 
 */
package project2;

import java.util.Random;

/**
 * @author Roshni Velluva Puthanidam,Joshua Palamuttam
 *Jun 24, 201611:27:43 AM
 */
public class TestTrees {
	
	public static void main(String[] args) throws Exception
    {
        if(args.length == 0 || args.length == 1 )
        {   
        	System.out.println("Incorrect number of arguments");
            System.out.println("part1");
            System.out.println("    bst - Runs insertion and removal for binary search trees");
            System.out.println("    avl - Runs insertion and removal for AVL trees");
            System.out.println();
            System.out.println("part2");
            System.out.println("    n - How many random numbers to generate for insertion");
            System.out.println("    k - How many random numbers to generate for searching");
            return;
        }

        switch(args[0])
        {
            case "part1":
                switch(args[1])
                {
                    case "bst":
                        testBST();
                        break;
                    case "avl":
                        testAVL();
                        break;
                }
                break;

            case "part2":
                treePerformance(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
                break;

        }
    }
	
	//Test BST Tree Insertion and removal
	private static void testBST()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int min = 10;
        int max = 99;

        Random rand = new Random();

        while(bst.height() < 5)
        {   
        	int data=rand.nextInt(max - min) + min;
        	//inserting unique integers
        	if(!bst.contains(data))
        	{
            bst.insert(data);
            }
            new TreePrinter(bst).print("Adding "+data+" To BST");
        }

        while(!bst.isEmpty())
        {
        	System.out.println("Removing Node "+bst.getRoot().getData()+" Of BST");
            bst.remove(bst.getRoot().getData());
            new TreePrinter(bst).print("");;
        }
    }
	
	//Test AVL Tree Insertion and removal
	private static void testAVL() throws Exception
    {
        AVLTree<Integer> avl = new AVLTree<>(true);
        int count=0;
        int min = 10;
        int max = 99;

        Random rand = new Random();

        for(int i = 0; i < 60; i++)
        {
            int data=rand.nextInt(max - min) + min;
            //checking for 35 unique numbers
            if(count==35){
            	break;
            }
            //inserting unique integer
        	if(!avl.contains(data))
        	{
        	avl.insert(data);
        	count++;
        	}
            if(!avl.isBalanced())
            {
                throw new Exception("Tree is not balanced after insertion");
            }
            new TreePrinter(avl).print("Adding "+data+" To AVL Tree");
            System.out.println("Number of elements inserted:"+count);
        }

        while(!avl.isEmpty())
        {
        	System.out.println("Removing Node "+avl.getRoot().getData()+" Of AVL");
            avl.remove(avl.getRoot().getData());
            if(!avl.isBalanced())
            {
                throw new Exception("Tree is not balanced after removal");
            }
            new TreePrinter(avl).print("");
        }
    }
	
	
	//performance comparison between BST and AVL
	private static void treePerformance(int n,int k) throws Exception{
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        AVLTree<Integer> avl = new AVLTree<>();
        
		Random rand=new Random();
		int min=0,max=1000;
		int[] randomIntsToSearch = new int[k];
		
        for(int i = 0; i < k; i++) {
          randomIntsToSearch[i] = rand.nextInt(max - min) + min;
        }
		System.out.println("Part2-First");
		//calculate insertion time for AVL
		double result = timeRunnable(() -> {
		    for( int i = 0; i < n; i++)
	        {
	            avl.insert(rand.nextInt(max - min) + min);
	            if(!avl.isBalanced())
	            {
	            	System.out.println("not balanced");
	            }
	        
	          //  new TreePrinter(avl).print("AVL Tree");
	        }
		 });
		 System.out.println("AVL Insertion Time for "+n+" data: " + result + "ms");	
		
		//calculate insertion time for BST
	     double result1 = timeRunnable(() -> {
			 for(int i = 0; i < n; i++)
		        {
		            bst.insert(rand.nextInt(max - min) + min);
		           
		        }
	     });
		 System.out.println("BST Insertion Time for "+n+" data: "+result1+"ms");
		 
		
		 System.out.println("");
		 System.out.println("Part2-Second");
		//calculate search time for BST
         result = timeRunnable(() -> {
           for(int val : randomIntsToSearch) {
           bst.contains(val);
           }
         });
         System.out.println("BST Search Time for "+k+" data: " + result + "ms where n=10000");
       
        //calculate search time for AVL
        result1 = timeRunnable(() -> {
          for(int val : randomIntsToSearch) {
           avl.contains(val);
          }
        });
        System.out.println("AVL Search Time for "+k+" data: " + result1 + "ms where n=10000");
        

//       combined time for search and insertion 
        System.out.println("");
        System.out.println("Part2-Third");
        result = timeRunnable(() -> {
        	for( int i = 0; i < n; i++)
	        {
	            avl.insert(rand.nextInt(max - min) + min);
	            if(!avl.isBalanced())
	            {
	            	System.out.println("not balanced");
	            }
	        
	          //  new TreePrinter(avl).print("AVL Tree");
	        }
            for(int val : randomIntsToSearch) {
            bst.contains(val);
            }
          });
        System.out.println("Time For AVL To Search "+k+" data and Insert "+n+" data " + result + "ms");
        
        result1 = timeRunnable(() -> {
			 for(int i = 0; i < n; i++)
		        {
		            bst.insert(rand.nextInt(max - min) + min);
		           
		        }
			 for(int val : randomIntsToSearch) {
		           bst.contains(val);
		           }
	     });
        
        System.out.println("Time For BST To Search "+k+" data and Insert "+n+" data " + result1 + "ms");
	}
		 
    //calculate the time taken for each processes
    private static double timeRunnable(Runnable runnable) {
        long startTime = System.nanoTime();
        runnable.run();
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000.0;
    }
	
}
	
        
        
           
       


    