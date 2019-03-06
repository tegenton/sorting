import java.util.ArrayList;
import java.util.Arrays;
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

			Comparable target = null;
			if (userSort == SortTypes.linear || userSort == SortTypes.binary) {
				while (true) {
					System.out.print("What is your target? ");
					if (useStrings) {
						target = in.nextLine();
						break;
					} else {
						try {
							target = Integer.parseInt(in.nextLine());
							break;
						} catch (Exception e) {
						}
					}
				}
			}

			System.out.print(userSort.toString() + ": ");

			int index;

			ArrayList<Comparable> dataList = new ArrayList<>();
			if (useLists) {
				dataList = new ArrayList<>(Arrays.asList(data));
				switch (userSort) {
				case bubble:
					dataList = bubble(dataList);
					break;
				case selection:
					dataList = selection(dataList);
					break;
				case insertion:
					dataList = insertion(dataList);
					break;
				case merge:
					dataList = merge(dataList);
					break;
				case linear:
					index = linear(dataList, target);
					System.out.println(index);
					break;
				case binary:
					index = binary(dataList, target);
					System.out.println(index);
					break;
				}
			}
			else {
				switch (userSort) {
					case bubble:
						data = bubble(data);
						break;
					case selection:
						data = selection(data);
						break;
					case insertion:
						data = insertion(data);
						break;
					case merge:
						data = merge(data);
						break;
					case linear:
						index = linear(data, target);
						System.out.println(index);
						break;
					case binary:
						index = binary(data, target);
						System.out.println(index);
						break;
				}
			}

			if (userSort != SortTypes.binary && userSort != SortTypes.linear) {
				if (useLists)
					dataList.forEach((item)->System.out.print(item + ", "));
				else {
					for (int i = 0; i < data.length - 1; i++) {
						System.out.print(data[i] + ", ");
					}
					System.out.println(data[data.length - 1]);
				}
			}

			System.out.println();

		}
	}

	public static Comparable[] bubble(Comparable[] list) {
		boolean swapped = false;
		Comparable temp;
		do {
			swapped = false;
			for (int i = 0; i < list.length - 1; i++) {
				if (list[i].compareTo(list[i + 1]) > 0) {
					temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;
					swapped = true;
				}
			}
		} while (swapped);
		return list;
	}
	public static ArrayList<Comparable> bubble(ArrayList<Comparable> list) {
		Comparable[] array = new Comparable[list.size()];
		array = list.toArray(array);
		return new ArrayList<Comparable>(Arrays.asList(bubble(array)));
	}

	public static Comparable[] selection(Comparable[] list) {
		Comparable temp;
		int min;
		for (int i = 0; i < list.length - 1; i++) {
			min = i;
			for (int j = i; j < list.length; j++) {
				if (list[j].compareTo(list[min]) < 0) {
					min = j;
				}
			}
			temp = list[i];
			list[i] = list[min];
			list[min] = temp;
		}
		return list;
	}
	public static ArrayList<Comparable> selection(ArrayList<Comparable> list) {
		Comparable[] array = new Comparable[list.size()];
		array = list.toArray(array);
		return new ArrayList<Comparable>(Arrays.asList(selection(array)));
	}

	public static Comparable[] insertion(Comparable[] list) {
		Comparable temp;
		for (int i = 1; i < list.length; i++) {
			temp = list[i];
			int j = i - 1;
			while (j >= 0 && list[j].compareTo(temp) > 0) {
				list[j + 1] = list[j--];
			}
			list[j + 1] = temp;
		}
		return list;
	}

	public static ArrayList<Comparable> insertion(ArrayList<Comparable> list) {
		Comparable[] array = new Comparable[list.size()];
		array = list.toArray(array);
		return new ArrayList<Comparable>(Arrays.asList(insertion(array)));
	}

	public static Comparable[] merge(Comparable[] list) {
		if (list.length == 1) {
			return list;
		}
		else {
			Comparable[] a = new Comparable[list.length/2];
			Comparable[] b = new Comparable[list.length - a.length];
			for (int i = 0; i < list.length; i++) {
				if (i < a.length)
					a[i] = list[i];
				else
					b[i - a.length] = list[i];
			}
			a = merge(a);
			b = merge(b);

			int aIndex, bIndex;
			aIndex = bIndex = 0;
			for (int i = 0; i < list.length; i++) {
				if (bIndex == b.length) {
					list[i] = a[aIndex++];
				}
				else if (aIndex == a.length) {
					list[i] = b[bIndex++];
				}
				else if (a[aIndex].compareTo(b[bIndex]) < 0) {
					list[i] = a[aIndex++];
				}
				else {
					list[i] = b[bIndex++];
				}
			}
		}
		return list;
	}

	public static ArrayList<Comparable> merge(ArrayList<Comparable> list) {
		Comparable[] array = new Comparable[list.size()];
		array = list.toArray(array);
		return new ArrayList<Comparable>(Arrays.asList(merge(array)));
	}

	public static int linear(Comparable[] list, Comparable target) {
		for (int i = 0; i < list.length; i++) {
			if (target.compareTo(list[i]) == 0) {
				return i;
			}
		}
		return -1;
	}

	public static int linear(ArrayList<Comparable> list, Comparable target) {
		Comparable[] array = new Comparable[list.size()];
		array = list.toArray(array);
		return linear(array, target);
	}

	public static int binary(Comparable[] list, Comparable target) {
		list = merge(list);
		int index = -1;
		int upper = list.length;
		int lower = 0;
		int m;
		while (lower <= upper) {
			m = (upper - lower) / 2 + lower;
			if (list[m].compareTo(target) == 0) {
				return m;
			}
			else if (list[m].compareTo(target) > 0) {
				upper = m - 1;
			}
			else {
				lower = m + 1;
			}
		}
		return index;
	}

	public static int binary(ArrayList<Comparable> list, Comparable target) {
		Comparable[] array = new Comparable[list.size()];
		array = list.toArray(array);
		return binary(array, target);
	}
}