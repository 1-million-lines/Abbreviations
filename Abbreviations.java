import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Abbreviations {
	public static void main(final String... args) throws IOException {
		Path path = Paths.get("days_of_week.txt");
		List<String> allLines = Files.readAllLines(path);
		for (int i = 0; i < allLines.size(); i++) {
			String line = allLines.get(i);
			if (line.length() == 0) continue;

			String[] days = line.split(" ");
			if (days.length != 7) 
				throw new RuntimeException("There are not 7 days on the line " + (i + 1));

			Map<String, Integer> temp = new HashMap<>();
			for (String day : days) {
				Integer count = temp.getOrDefault(day, 0);
				temp.put(day, count + 1);
			}
			if (temp.size() < 7) {
				System.out.print(" ∞  ");
				System.out.println(line);
				continue;
			}

			int len = 1;
			while (true) {
				temp.clear();
				for (String day : days) {
					String sd;
					if (len >= day.length()) {
						sd = day;
					} else {
						sd = day.substring(0, len);
					}
					Integer count = temp.getOrDefault(sd, 0);
					temp.put(sd, count + 1);
				}

				if (temp.size() == 7) {
					System.out.printf("%2d %s\n", len, line);
					break;
				}
				len++;
			}
		}
	}
}