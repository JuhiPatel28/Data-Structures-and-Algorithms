import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Juhi Patel
 * Class: 3345
 * Section: 003
 * Semester Spring 2019
 * Project 3 - LazyBinarySearchTree
 */

public class Proj3 
{
	public static void main(String[] args)
	{
		//get file from user
		Scanner in = new Scanner(System.in);
		String filename = args[0];
		String outfilename = args[1];
		
		//file I/O
		BufferedReader reader;
		FileWriter writer;
		File file = new File(filename);
		
		try 
		{
			//Opening file I/O streams
		    reader = new BufferedReader(new FileReader(file));
		    writer = new FileWriter(outfilename);
		    PrintWriter pw = new PrintWriter(writer);
		    
		    String text;
	    	LazyBinarySearchTree tree = new LazyBinarySearchTree();
	    	while((text = reader.readLine()) != null) //loop until the end of the file
	    	{
	    		if(text.equals("PrintTree")) //print if given the command
	    		{
	    			writer.write(tree.tf);
	    			writer.write(tree.toString());
	    			writer.write("\n");
	    			tree.tf = ""; //reset true/false string
	    		}
	    		else if(text.equals("FindMin")) //find min if given command
	    		{
	    			String min =Integer.toString(tree.findMin());
	    			if(min.equals("-1"))
	    			{
	    				writer.write("Min value not found. \n");
	    			}
	    			else
	    			{
	    				writer.write(min);
	    				writer.write("\n");		    		
	    			}			
	    		}
	    		else if(text.equals("FindMax")) //find max if given command
	    		{
	    			String max =Integer.toString(tree.findMax());
	    			if(max.equals("-1"))
	    			{
	    				writer.write("Max value not found. \n");
	    			}
	    			else
	    			{
	    				writer.write(max);
	    				writer.write("\n");			    		
	    			}
	    		}
	    		else if(text.equals("Height")) //find hight if given command
	    		{
	    			String height =Integer.toString(tree.height());
	    			writer.write(height);
	    			writer.write("\n");
	    		}
	    		else if(text.equals("Size")) //find size if given command
	    		{
	    			String size =Integer.toString(tree.size());
	    			writer.write(size);
	    			writer.write("\n");
	    		}
	    		else if(text.contains(":")) //if there is a colon
	    		{
	    			//separate the command from the string
	    			String command = text.substring(0, text.indexOf(":"));
	    			int data = Integer.parseInt(text.substring(text.indexOf(":")+1, text.length()));
	    			
	    			//check which command and perform accordingly
	    			if(command.equals("Insert"))
	    			{		
	    				try
	    				{
	    					tree.insert(data);
	    					writer.write(tree.tf);
		    				tree.tf = ""; //reset true/false string
	    				}
	    				catch(Exception e)
	    				{
	    					pw.println(e);
	    				}
	    				
	    			}
	    			else if(command.equals("Contains"))
	    			{
	    				try
	    				{
		    				tree.contains(data);
		    				writer.write(tree.tf);
		    				tree.tf = ""; //reset true/false string
	    				}
	    				catch(Exception e)
	    				{
	    					pw.println(e);
	    				}
	    			}
	    			else if(command.equals("Delete"))
	    			{
	    				try 
	    				{
	    					tree.delete(data);
	    					writer.write(tree.tf);
		    				tree.tf = ""; //reset true/false string
	    				}
	    				catch(Exception e)
	    				{
	    					pw.println(e);
	    				}	    			
	    			}
	    			else //if none of the valid commands
	    			{
	    				writer.write("Error in Line: " + text + "\n");
	    			}
	    		}
	    		else
	    		{
	    			writer.write("Error in Line: " + text + "\n");
	    		}
	    	}
	    	writer.close();	
		} 
		catch (FileNotFoundException e) 
		{
		    System.out.println("File not found.");
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
}
