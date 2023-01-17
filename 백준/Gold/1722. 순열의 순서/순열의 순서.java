
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static long[] factorial;
	public static long idx;
	public static List<Integer> numbers;
	public static int[] inputNumbers;
	public static int[] result1;
	public static long result2;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		factorial = new long[n+1];
		factorial[0] = 1;
		factorial[1] = 1;
		fillFactorial();
		
		st = new StringTokenizer(br.readLine());
		numbers = new ArrayList<>();
		for (int i = 1; i < n+ 1;i++) {
			numbers.add(i);
		}
		if (Integer.parseInt(st.nextToken()) == 1) {
			idx = Long.parseLong(st.nextToken()) -1 ;
			result1 = new int[n];
			find(n);
			for (int i = 0 ;i < n; i++) {
				System.out.print(result1[i] + " ");
			}
		} else {
			inputNumbers = new int[n];
			for (int i = 0; i < n; i++) {
				inputNumbers[i] = Integer.parseInt(st.nextToken());
			}
			
			findReverse(n);
			System.out.println(result2+1);
		}
		
		
		
		
		
	}
	
	public static void findReverse(int index) {
		if (index == 0) {
			return;
		}
		int realIdx = n - index;
		result2 += numbers.indexOf(inputNumbers[realIdx]) * factorial[index-1];
		numbers.remove(numbers.indexOf(inputNumbers[realIdx]));
		findReverse(index - 1);
	}
	
	public static void find(int index) {
		if (index == 0) {
			return;
		}
		int realIdx = n - index;
		int getIdx = (int) (idx / factorial[index - 1]);
		result1[realIdx] = numbers.get(getIdx);
		numbers.remove(getIdx);
		idx = idx % factorial[index-1];
		find(index - 1);
	}
	
	public static void fillFactorial() {
		for (int i = 2; i< n + 1; i++) {
			factorial[i] = factorial[i-1] * i;
		}
	}
}
