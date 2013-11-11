package dictionary;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPETITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
 
    	PrintWriter BST_writer = new PrintWriter("BinarySearchTree.dat", "UTF-8");
        PrintWriter OLL_writer = new PrintWriter("OrderedLinkedList.dat", "UTF-8");
    	InsertComplexities ic = new InsertComplexities(new Random());
    	BinarySearchTree<InsertComplexities.InstrumentedKey, Integer> bst 
    		= new BinarySearchTree<InsertComplexities.InstrumentedKey, Integer>();
    	OrderedLinkedList<InsertComplexities.InstrumentedKey, Integer> oll 
    		= new OrderedLinkedList<InsertComplexities.InstrumentedKey, Integer>();
        int[] bstComplexities = ic.getInsertComplexities(bst, MAX_SIZE, REPETITIONS);
        int[] ollComplexities = ic.getInsertComplexities(oll, MAX_SIZE, REPETITIONS);
        for (int i = 0; i < MAX_SIZE; ++i)
        {
        	BST_writer.println(i + "\t" + bstComplexities[i]);
        	OLL_writer.println(i + "\t" + ollComplexities[i]);
        }
        BST_writer.close();
        OLL_writer.close();	
    }

}
