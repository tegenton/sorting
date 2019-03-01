import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SearchAndSort {

	private enum SortTypes {
		bubble,
		selection,
		insertion,
		merge,
		linear,
		binary,
		quit
	}


	/**
	 * Program execution starts here.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
		    String input;
		    // get a valid sort from user
			SortTypes userSort = null;
			while (userSort == null) {
				System.out.print("What algorithm would you like to execute? ");
				input = in.nextLine();
				try {
					userSort = SortTypes.valueOf(input);
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid Sort");
				}
			}

			// quit
			if (userSort == SortTypes.quit)
				return;

			// data type
			boolean useStrings;
			while (true) {
				System.out.print("What type of data? ");
				try {
					input = in.nextLine().toLowerCase();
					if (input.equals("strings")) {
						useStrings = true;
					}
					else if (input.equals("ints")) {
						useStrings = false;
					}
					else {
						System.out.println("Invalid Type");
						continue;
					}
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid Type");
				}
			}

			// array or list
			boolean useLists;
			while (true) {
				System.out.print("How is it stored? ");
				try {
					input = in.nextLine().toLowerCase();
					if (input.equals("list")) {
						useLists = true;
					}
					else if (input.equals("array")) {
						useLists = false;
					}
					else {
						System.out.println("Invalid option");
						continue;
					}
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid option");
				}
			}

			// get data
            Comparable[] data;
			while (true) {
				System.out.print("Enter the Data. ");
				String unparsed = in.nextLine();
				if (useStrings) {
					String[] parsed = unparsed.split(",");
					data = parsed;
				}
				else {
					Integer[] parsed = new Integer[unparsed.split(",").length];
					int i = 0;
					try {
						for (String element : unparsed.split(",")) {
							parsed[i++] = Integer.parseInt(element);
						}
					} catch (NumberFormatException E) {
						continue;
					}
					data = parsed;
				}
				break;
			}

			ArrayList<Comparable> dataList = new ArrayList<>();
			if (useLists) {
				dataList = (ArrayList<Comparable>) Arrays.asList(data);
				switch (userSort) {
				case bubble:
					bubble(dataList);
				case selection:
					selection( dataList);
				case insertion:
					insertion(dataList);
				case merge:
					merge(dataList);
				case linear:
					linear(dataList);
				case binary:
					binary(dataList, target);
			}
			}
			switch (userSort) {
				case bubble:
					bubble(data);
				case selection:
					selection( data);
				case insertion:
					insertion(data);
				case merge:
					merge(data);
				case linear:
					linear(data);
				case binary:
					binary(data, target);
			}
		}
	}
	public static <E extends Comparable<E>> E bubble(E[] list) {

	}
	public static <E extends Comparable<E>> ArrayList<E> bubble(ArrayList<E> list) {

	}

}