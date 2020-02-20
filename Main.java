
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	static int booksNum;
	static int libraryNum;
	static int daysNum;

	static Map<Integer, Integer> booksScore;

	static Map<Integer, Boolean> booksIds;

	static List<Library> libraries;

	static String[] inputFiles = { "a_example.txt", "b_read_on.txt", "c_incunabula.txt", "d_tough_choices.txt",
			"e_so_many_books.txt", "f_libraries_of_the_world.txt", };

	public static void main(String[] args) {
		for (int k = 0; k < 6; k++) {
			booksIds = new HashMap<>();
			MyFileReader reader = new MyFileReader(inputFiles[k]);
			booksNum = reader.nextInt();
			libraryNum = reader.nextInt();
			daysNum = reader.nextInt();

			booksScore = new HashMap<>();
			libraries = new ArrayList<>();
			for (int i = 0; i < booksNum; i++) {
				booksScore.put(i, reader.nextInt());
				booksIds.put(i, false);
			}

			for (int i = 0; i < libraryNum; i++) {
				int booksNum = reader.nextInt();
				int singUpDays = reader.nextInt();
				int booksPerDay = reader.nextInt();
				Library library = new Library(i, booksNum, singUpDays, booksPerDay);

				for (int j = 0; j < booksNum; j++) {
					int id = reader.nextInt();
					library.addBook(id, booksScore.get(id));
				}
				libraries.add(library);
			}
			Collections.sort(libraries, (l1, l2) -> {
				if (l1.singUpDays != l2.singUpDays)
					return l1.singUpDays - l2.singUpDays;
				else if(l2.booksPerDay != l1.booksPerDay)
					return l2.booksPerDay - l1.booksPerDay;
				else
					return l2.booksNum - l1.booksNum;
			});

			List<Integer> books;
			List<Library> toSemulatelibraries = new ArrayList<>();
			for (int i = 0; i < libraryNum; i++) {
				books = new ArrayList<>();
				if (i != 0) {
					for (int id : libraries.get(i).books) {
						if (!booksIds.get(id)) {
							books.add(id);
						}
					}
					if (books.size() != 0){
						libraries.get(i).books = books;
						toSemulatelibraries.add(libraries.get(i));
						Collections.sort(libraries.get(i).books, (b1, b2) -> {
							return booksScore.get(b2) - booksScore.get(b1);
						});
					}
				} else{
					toSemulatelibraries.add(libraries.get(i));
					Collections.sort(libraries.get(i).books, (b1, b2) -> {
						return booksScore.get(b2) - booksScore.get(b1);
					});
				}
				
				int numOfBooksForScanning = libraries.get(i).books.size();

				for (int j = 0; j < numOfBooksForScanning; j++) {
					booksIds.put(libraries.get(i).books.get(j), true);
				}
			}
			int i = 0;
			List<Library> toKeeplibraries = new ArrayList<>();
			while(daysNum > 0 && i < toSemulatelibraries.size()){
				if(daysNum - toSemulatelibraries.get(i).singUpDays <= 0){
					i++;
					continue;
				}
				daysNum -= toSemulatelibraries.get(i).singUpDays;
				BigInteger totalBooksCap = new BigInteger(""+(toSemulatelibraries.get(i).booksPerDay * daysNum));
				if(totalBooksCap.compareTo(new BigInteger("" + (toSemulatelibraries.get(i).books.size()))) == -1 && totalBooksCap.intValue() > 0){
					toSemulatelibraries.get(i).books = toSemulatelibraries.get(i).books.subList(0, totalBooksCap.intValue());
				}
				toSemulatelibraries.get(i).reCalcTotal();
				toKeeplibraries.add(toSemulatelibraries.get(i));
				i++;
			}
			Collections.sort(toKeeplibraries, (l1, l2) -> {
				if (l1.singUpDays != l2.singUpDays)
					return l1.singUpDays - l2.singUpDays;
				return l2.totalScore - l1.totalScore;
			});
			printOutput(toKeeplibraries, k);
			
		}
	}

	static class Library {
		int id;
		int booksNum;
		int singUpDays;
		int booksPerDay;

		int totalScore;

		List<Integer> books;

		public Library(int id, int booksNum, int singUpDays, int booksPerDay) {
			this.id = id;
			this.booksNum = booksNum;
			this.singUpDays = singUpDays;
			this.booksPerDay = booksPerDay;
			this.books = new ArrayList<>();
			this.totalScore = 0;
		}

		public void addBook(int bookId, int score) {
			this.books.add(bookId);
			this.totalScore += score;
		}

		public void reCalcTotal(){
			this.totalScore = 0;
			for(int i = 0; i < this.books.size(); i++){
				this.totalScore += booksScore.get(this.books.get(i));
			}
		}
	}

	static void printOutput(List<Library> libraries, int k) {
		File outFile = new File("output_" + inputFiles[k]);
		try {
			outFile.createNewFile();
			FileWriter writer = new FileWriter(outFile);
			int size = libraries.size();
			writer.append(size + "\n");
			for (int i = 0; i < size; i++) {
				int bookSize = libraries.get(i).books.size();
				writer.append(libraries.get(i).id + " " + bookSize + "\n");
				for (int j = 0; j < bookSize; j++) {
					writer.append(libraries.get(i).books.get(j) + "");
					if (j != bookSize - 1)
						writer.append(" ");
					else
						writer.append("\n");
				}
			}
			System.out.println(outFile.getName());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
