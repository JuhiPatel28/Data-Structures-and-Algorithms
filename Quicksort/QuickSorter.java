

import java.util.*;
import java.time.Duration;

public class QuickSorter
{	
	public static <E extends Comparable <E>> Duration timedQuickSort(ArrayList<Integer> list, PivotStrategy pivotStrategy)
	{
		long startTime;
		long endTime;
		Duration elapsedTime = Duration.ofNanos(0);
		
		//if given null input
		if(list == null || pivotStrategy == null)
		{
			throw new NullPointerException("Cannot have null arguments.");
		}
		
		//if small list
		if(list.size() < 20)
		{
			startTime = System.nanoTime();
			insertionSort(list);
			endTime = System.nanoTime();
			elapsedTime = Duration.ofNanos(endTime - startTime);
		}
		else
		{
			switch(pivotStrategy)
			{
				case FIRST_ELEMENT:
				{
					startTime = System.nanoTime();
					FirstQS(list, 0, list.size() - 1);
					endTime = System.nanoTime();
					elapsedTime = Duration.ofNanos(endTime - startTime);
				}
				case RANDOM_ELEMENT:
				{
					startTime = System.nanoTime();
					RandomQS(list, 0, list.size() - 1);
					endTime = System.nanoTime();
					elapsedTime = Duration.ofNanos(endTime - startTime);
				}
				case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
				{
					startTime = System.nanoTime();
					RandomMedThreeQS(list, 0, list.size() - 1);
					endTime = System.nanoTime();
					elapsedTime = Duration.ofNanos(endTime - startTime);
				}
				case MEDIAN_OF_THREE_ELEMENTS:
				{
					startTime = System.nanoTime();
					MedThreeQS(list, 0, list.size() - 1);
					endTime = System.nanoTime();
					elapsedTime = Duration.ofNanos(endTime - startTime);
				}
			}
		}
		return elapsedTime;
	}
	
	public static ArrayList<Integer>generateRandomList(int size) //generate random list
	{
		if(size <= 0)
		{
			throw new IllegalArgumentException("Size cannot be a less than or equal to 0.");
		}
		
		ArrayList<Integer> list = new ArrayList<>(size);
		
		Random random = new Random();
		
		for (int i = 0; i < size; ++i)
		{
            int num = random.nextInt((size + 100));
            list.add(num);
        }

		return list;
	}
	
	public static enum PivotStrategy
	{
		FIRST_ELEMENT,
		RANDOM_ELEMENT,
		MEDIAN_OF_THREE_RANDOM_ELEMENTS,
		MEDIAN_OF_THREE_ELEMENTS	
	}
	
	public static void insertionSort(ArrayList<Integer> list) //insertion sort
	{
		
		int size = list.size();
		for(int counter = 1; counter < size; counter++)
		{
			int temp = list.get(counter);
			int counter2 = counter-1;
			
			while(counter2 >= 0 && list.get(counter2) > temp)
			{
				list.set(counter2 + 1, list.get(counter2));
				counter2 = counter2 - 1;
			}
			
			list.set(counter2 + 1, temp);
		}
	}
	
	//all quicksorts
	public static int FirstP(ArrayList<Integer> list, int low, int high, int p)
	{
		p = low;
		int highIndex = low + 1;
		
		for(int counter = low + 1; counter <= high; counter++)
		{
			if(list.get(counter).compareTo(list.get(p)) < 0)
			{
				Collections.swap(list, counter, highIndex);
				highIndex++;
			}
		}
		
		Collections.swap(list, p, highIndex - 1);
		p = highIndex - 1;
		return p;
	}
		
	public static void FirstQS(ArrayList<Integer> list, int low, int high)
	{
		if(high - low < 1)
		{
			return;
		}
		
		int p = low;
		int pivot = FirstP(list, low, high, p);
		
		FirstQS(list, low, pivot - 1);
		FirstQS(list, pivot + 1, high);
	}
		
	public static int RandomP(ArrayList<Integer> list, int low, int high, int p)
	{
		Collections.swap(list, low, p);
		p = low;
		int highIndex = low + 1;
		
		for(int counter = low + 1; counter <= high; counter++)
		{
			if(list.get(counter).compareTo(list.get(p)) < 0)
			{
				Collections.swap(list, counter, highIndex);
				highIndex++;
			}
		}
		
		Collections.swap(list, p, highIndex - 1);
		p = highIndex - 1;
		return p;
	}
	
	
	public static void RandomQS(ArrayList<Integer> list, int low, int high)
	{
		if(high - low < 1)
		{
			return;
		}
		
		Random r = new Random();
		int p = r.nextInt((high - low) + 1) + low;
		int pivot = RandomP(list, low, high, p);
		
		RandomQS(list, low, pivot - 1);
		RandomQS(list, pivot + 1, high);
	}
	
	public static int RandomMedThreeP(ArrayList<Integer> list, int low, int high, int p)
	{
		Collections.swap(list, low, p);
		p = low;
		int highIndex = low + 1;
		
		for(int counter = low + 1; counter <= high; counter++)
		{
			if(list.get(counter).compareTo(list.get(p)) < 0)
			{
				Collections.swap(list, counter, highIndex);
				highIndex++;
				for(int counter1 = 0; counter1 < list.size(); counter1++);
			}
		}
		
		Collections.swap(list, p, highIndex - 1);
		p = highIndex - 1;
		return p;
	}
		
	public static void RandomMedThreeQS(ArrayList<Integer> list, int low, int high)
	{
		if(high - low < 1)
		{
			return;
		}
		
		Random r = new Random();
		int a = r.nextInt((high - low) + 1) + low;
		int b = r.nextInt((high - low) + 1) + low;
		int c = r.nextInt((high - low) + 1) + low;
		int p = Math.max(Math.min(a,b), Math.min(Math.max(a,b),c));
		
		int pivot = RandomMedThreeP(list, low, high, p);
		
		RandomMedThreeQS(list, low, pivot - 1);
		RandomMedThreeQS(list, pivot + 1, high);
	}
	
	
	
	public static int MedThreeP(ArrayList<Integer> list, int low, int high, int p)
	{
		Collections.swap(list, low, p);
		p = low;
		int highIndex = low + 1;
		
		for(int counter = low + 1; counter <= high; counter++)
		{
			if(list.get(counter).compareTo(list.get(p)) < 0)
			{
				Collections.swap(list, counter, highIndex);
				highIndex++;
			}
		}
		
		Collections.swap(list, p, highIndex - 1);
		p = highIndex - 1;
		return p;
	}
	
	
	public static void MedThreeQS(ArrayList<Integer> list, int low, int high)
	{
		if(high - low < 1)
		{
			return;
		}
		
		int a = low;
		int b = low/high;
		int c = high;
		int p = Math.max(Math.min(a,b), Math.min(Math.max(a,b),c));
		
		int pivot = MedThreeP(list, low, high, p);
		
		MedThreeQS(list, low, pivot - 1);
		MedThreeQS(list, pivot + 1, high);
	}	
	
}
