

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class Proj5 
{
	public static void main(String[] args)
	{
		//get user input
		Scanner in = new Scanner(System.in);
		int size = Integer.parseInt(args[0]);
		String report = args[1];
		String unsorted = args[2];
		String sorted = args[3];	
		Duration duration1 = null, duration2 = null, duration3 = null, duration4 = null;
		
		ArrayList<Integer> original = QuickSorter.generateRandomList(size);		
		
		//write unsorted list
		FileWriter writer;
		try 
		{
			writer = new FileWriter(unsorted);
			for(int counter = 0; counter < original.size(); counter++)
			{
				writer.write(original.get(counter) + " ");
			}
			writer.close();	
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		ArrayList<Integer> list3 = new ArrayList<>();
		ArrayList<Integer> list4 = new ArrayList<>();
		
		//duplicate list
		for(int n : original)
		{
			list1.add(n);
			list2.add(n);
			list3.add(n);
			list4.add(n);
		}	
		
		//sort
		try
		{		
			duration1 = QuickSorter.timedQuickSort(list1, QuickSorter.PivotStrategy.FIRST_ELEMENT);
		}
		catch (StackOverflowError e)
		{
			System.out.println("FIRST_ELEMENT pivot strategy overflowed.");
		}
		try
		{
			duration2 = QuickSorter.timedQuickSort(list2, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
		}
		catch (StackOverflowError e)
		{
			System.out.println("MEDIAN_OF_THREE_ELEMENTS pivot strategy overflowed.");
		}
		try
		{
			duration3 = QuickSorter.timedQuickSort(list3, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
		}
		catch (StackOverflowError e)
		{
			System.out.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS pivot strategy overflowed.");
		}
		try
		{
			duration4 = QuickSorter.timedQuickSort(list4, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
		}
		catch (StackOverflowError e)
		{
			System.out.println("RANDOM_ELEMENT pivot strategy overflowed.");
		}	
		
		//write to report
		try 
		{
			writer = new FileWriter(report);
			writer.write("Array Size = " + size + "\n");
			writer.write("FIRST_ELEMENT : " + duration1 + "\n");
			writer.write("RANDOM_ELEMENT : " + duration4 + "\n");
			writer.write("MEDIAN_OF_THREE_RANDOM _ELEMENTS " + duration3 + "\n");
			writer.write("MEDIAN_OF_THREE_ELEMENTS " + duration2 + "\n");
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//write to sorted file
		try 
		{
			writer = new FileWriter(sorted);
			for(int counter = 0; counter < original.size(); counter++)
			{
				writer.write(list2.get(counter) + " ");
			}
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		

	}

}
