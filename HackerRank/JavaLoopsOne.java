
import java.util.Scanner;

public class JavaLoopsOne {
	public static void main(String[] args) {

		class Solution {
			public static void main(String[] argh) {
				Scanner in = new Scanner(System.in);
				int t = in.nextInt();
				var num1 = 0;
				var num2 = 0;
				for (int i = 0; i < t; i++) {
					int a = in.nextInt();
					int b = in.nextInt();
					int n = in.nextInt();

					for (int k = 0; k < n; k++) {
						num1 = a;
						num2 += (Math.pow(2, k) * b);
						System.out.print(num1 + num2 + " ");

					}
				}
				in.close();
			}
		}
	}
}
