import java.util.HashMap;
import java.util.List;

public class MemoHelper {
	HashMap<List<Integer>, Integer> memoed;
	MemoHelper(){
		memoed = new HashMap<List<Integer>, Integer>();
	}
	
	public Integer check 
	(List<Integer> numbers)
	{
		return memoed.get(numbers);
	}
	
//	public boolean checkIfExsit(List<Integer> numbers)
//	{
//		
//		return false;
//	}
	
	public void add(List<Integer> key, Integer value)
	{
		memoed.put(key, value);
	}
}
