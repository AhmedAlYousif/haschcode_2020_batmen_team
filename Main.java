
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	static int booksNum;
	static int libraryNum;
	static int daysNum;

	static Map<Integer, Integer> booksScore;

	static List<Library> libraries;

	static String[] inputFiles = { "a_example.txt", "b_read_on.txt", "c_incunabula.txt", "d_tough_choices.txt",
			"e_so_many_books.txt", "f_libraries_of_the_world.txt", };

	public static void main(String[] args) {
		for (int k = 0; k < 6; k++) {
			MyFileReader reader = new MyFileReader(inputFiles[k]);
			booksNum = reader.nextInt();
			libraryNum = reader.nextInt();
			daysNum = reader.nextInt();

			booksScore = new HashMap<>();
			libraries = new ArrayList<>();
			for (int i = 0; i < booksNum; i++) {
				booksScore.put(i, reader.nextInt());
			}

			for (int i = 0; i < libraryNum; i++) {
				int booksNum = reader.nextInt();
				int singUpDays = reader.nextInt();
				int booksPerDay = reader.nextInt();
				Library library = new Library(i, booksNum, singUpDays, booksPerDay);

				for (int j = 0; j < booksNum; j++) {
					library.books[j] = reader.nextInt();
				}
				libraries.add(library);
			}
			Collections.sort(libraries, (l1, l2) -> {
				if(l1.booksPerDay != l2.booksPerDay)
					return l2.booksPerDay - l1.booksPerDay;
				return l1.singUpDays - l2.singUpDays;
			});
			File outFile = new File("output_" + inputFiles[k]);
			try {
				outFile.createNewFile();
				FileWriter writer = new FileWriter(outFile);
				writer.append(libraryNum + "\n");
				for (int i = 0; i < libraryNum; i++) {
					writer.append(libraries.get(i).id + " " + libraries.get(i).booksNum + "\n");
					for (int j = 0; j < libraries.get(i).booksNum; j++) {
						writer.append(libraries.get(i).books[j] + "");
						if (j != libraries.get(i).booksNum - 1)
							writer.append(" ");
						else
							writer.append("\n");
					}
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static class Library {
		int id;
		int booksNum;
		int singUpDays;
		int booksPerDay;

		int[] books;

		public Library(int id, int booksNum, int singUpDays, int booksPerDay) {
			this.id = id;
			this.booksNum = booksNum;
			this.singUpDays = singUpDays;
			this.booksPerDay = booksPerDay;
			this.books = new int[booksNum];
		}
	}

}
