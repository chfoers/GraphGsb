
package de.materna.GraphPattern;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Filesystem implements Source {

	public static File folder = new File("C:\\Users\\cfoerste\\Desktop\\content\\standardlsg");
	static List<String> result = new ArrayList<>();

	public Filesystem() {
	}

	public static void main(String[] args) {
//		getList();
	}

	@Override
	public ArrayList<String> getList() {
		ArrayList<String> list = new ArrayList<String>();
		search(".*\\.xml", folder, result);
		for (String s : result) {

			list.add(s);
		}
		return list;
	}

	public static void search(final String pattern, final File folder, List<String> result) {
		for (final File f : folder.listFiles()) {

			if (f.isDirectory()) {
				search(pattern, f, result);
			}

			if (f.isFile()) {
				if (f.getName().matches(pattern)) {
					result.add(f.getAbsolutePath());
				}
			}

		}
	}

	@Override
	public ArrayList<String> getList2() {
		// TODO Auto-generated method stub
		return null;
	}

}
