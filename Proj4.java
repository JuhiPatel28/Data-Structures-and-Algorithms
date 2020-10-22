

import java.io.*;
import java.util.*;

public class Proj4 
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
		    String text;
		    if((text = reader.readLine()) == null) //get first line of file
		    {
		    	System.out.println("Empty File.");
		    	return;
		    }
		    	
		    if(text.equals("Integer")) //if the first line indicates integer
		    {
		    	//create a redblacktree of integer type
		    	RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		    	while((text = reader.readLine()) != null) //loop until the end of the file
		    	{
		    		if(text.equals("PrintTree")) //print if given the command
		    		{
		    			writer.write(tree.tf);
		    			writer.write(tree.toString());
		    			writer.write("\n");
		    			tree.tf = ""; //reset true/false string
		    		}
		    		else if(text.contains(":")) //if there is a colon
		    		{
		    			//separate the command from the string
		    			String command = text.substring(0, text.indexOf(":"));
		    			int data = Integer.parseInt(text.substring(text.indexOf(":")+1, text.length()));
		    			
		    			//check which command and perform accordingly
		    			if(command.equals("Insert"))
		    			{		    				
		    				tree.insert(data);
		    			}
		    			else if(command.equals("Contains"))
		    			{
		    				tree.contains(data);
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
		    else if(text.equals("String")) //if string data type
		    {
		    	//create redblacktree of string type
		    	RedBlackTree<String> tree = new RedBlackTree<String>();
		    	while((text = reader.readLine()) != null) //loop until end of file
		    	{
		    		if(text.equals("PrintTree")) //if command to print, print
		    		{
		    			writer.write(tree.tf);
		    			writer.write(tree.toString());
		    			writer.write("\n");
		    			tree.tf = ""; //reset true/false string
		    		}
		    		else if(text.contains(":")) //if there is a colon
		    		{
		    			//separate command from string
		    			String command = text.substring(0, text.indexOf(":"));
		    			String data = text.substring(text.indexOf(":")+1, text.length());
		    			//perform command accordingly
		    			if(command.equals("Insert"))
		    			{		    				
		    				tree.insert(data);
		    			}
		    			else if(command.equals("Contains"))
		    			{
		    				tree.contains(data);
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
		    else //if not one of the right data types
		    {
		    	writer.write("Only works for objects Integers and Strings"); //display error msg
		    	writer.close();
		    	return;
		    }
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
