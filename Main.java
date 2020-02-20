
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFileReader reader = new MyFileReader("a_example.in");
		//System.out.println(reader.readAll());
	
		List<Integer> numbers = reader.readAsIntList();
		System.out.println("max:"+numbers.get(0));
		System.out.println("n:"+numbers.get(1));
		numbers.remove(0);
		numbers.remove(1);
		//printIntList(numbers);
		
		
		MemoHelper mh = new MemoHelper();
		
		List<Integer> l1 = ListHelper.fillIntList(3);
		List<Integer> l2 = ListHelper.fillIntList(3);
		mh.add(l1,6);
		System.out.println(mh.check(l2));
		
	}
	
}
