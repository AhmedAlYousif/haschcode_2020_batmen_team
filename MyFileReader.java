import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyFileReader {
	Scanner myReader;
	public MyFileReader(String fileName) {
		
		try {
		      File myObj = new File(fileName);
		      myReader = new Scanner(myObj);
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred while opening file");
		      //e.printStackTrace();
		    }
	}
	
	public String readAll()
	{
		try {	      
			 String data = "";
		      while (myReader.hasNextLine()) {
		        data += myReader.nextLine();
		        //System.out.println(data);
		      }
		      myReader.close();
		      return data;
		    } catch (Exception e) {
		      System.out.println("An error occurred while reading");
		      //e.printStackTrace();
		    }
		return "";
	}
	
	public List<Integer> readAsIntList ()
	{
		int numbersRead = 0;
		List<Integer> numbers = new ArrayList<>();
		String i = read();
		while (!i.equals(""))
		{
			try
			{
			
			int f = Integer.parseInt(i);
			numbers.add(f);
			numbersRead++;
			i = read();
			}
			catch(NumberFormatException e)
			{
				
				
			}
		}
		myReader.close();
		System.out.println("reached end of numbers list. NumbersRead");
		return numbers;
	}
	public String read()
	{
		
		if (myReader.hasNext())
			return myReader.next();
		else
			return "";
	}
	
	public void close()
	{
		myReader.close();
	}
}
