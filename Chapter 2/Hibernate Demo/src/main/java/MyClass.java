

public class MyClass {
	public static void main(String[] args) throws Exception {
		
		if (args.length == 0) {
			System.out.println("1");
			//System.out.println("12");
			//throw new Exception("Command invoked successfully with failed status: ");
		} else if (args.length == 1) {
			System.out.println("0");
			//System.out.println("02");
		}
		
	}
}
