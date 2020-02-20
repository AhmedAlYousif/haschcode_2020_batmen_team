import java.util.ArrayList;
import java.util.List;

public class ListHelper {
	public static void printIntList(List<Integer> numbers)
	{
		for (int j = 0; j < numbers.size(); j++) {
			System.out.println(j+":"+numbers.get(j));
		}
	}
	
	public static List<Integer> fillIntList(int size)
	{
		List<Integer> numbers = new ArrayList<Integer>();
		for (int j = 0; j < size; j++) {
			numbers.add(j);
		}
		return numbers;
	}
}
