
public class scratch {

	public static void main(String[] args) {
		int[] data = {1,2,3,4,4};
		int item = 4;
		int indexOfChild;
		for (indexOfChild = 0; indexOfChild < data.length; indexOfChild++) {
			if (data[indexOfChild] > item) {
				break;
			}	
		}	
		System.out.print(indexOfChild);
	}

}
