package phaserush;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) throws IOException {
        final Scanner sc = new Scanner(System.in);
        final String path = sc.nextLine();
        final LinkedHashMap<String, Integer> tree = new LinkedHashMap<>();
        final String[] parse = new String[1];
        Files.lines(Paths.get(path))
                .forEach(line -> {
                    try {
                        String[] arr = line.split(" ");
                        tree.put(arr[1], Integer.valueOf(arr[0]));
                    } catch (Exception ignored) {
                        if (line.matches("[^0-9]*")) {
                            parse[0] = line;
                        }
                    }
                });

        final String input = parse[0];
        final StringBuilder output = new StringBuilder();
        int sEnd = 1;
        String s = input.substring(0, sEnd);
        String c;
        while (sEnd < input.length()) {
            c = input.substring(sEnd, sEnd + 1);
            if (tree.containsKey(s + c)) {
                s = s + c;
                sEnd++;
            } else {
                output.append(tree.get(s));
                tree.put(s + c, tree.size());
                s = c;
                sEnd++;
            }
        }
        System.out.println(output);
        tree.forEach((k, v) -> System.out.println(v + "\t" + k));
    }
}
