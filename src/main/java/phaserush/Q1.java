package phaserush;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) throws IOException {
        final Scanner sc = new Scanner(System.in);
        final String path = sc.nextLine();
        final Map<String, Character> tree = new HashMap<>();
        final String[] parse = new String[1];
        Files.lines(Paths.get(path))
                .skip(1)
                .forEach(line -> {
                    try {
                        String[] arr = line.split(" ");
                        tree.put(arr[1], arr[0].charAt(0));
                    } catch (Exception ignored) {
                        parse[0] = line;
                    }
                });

//        tree.forEach((k, v) -> System.out.println(k + "\t" + v));

        String target = parse[0];
        final StringBuilder sb = new StringBuilder();
        String subTarget;
        int endIdx = 0;
        while (target.length() != 0) {
            subTarget = target.substring(0, endIdx);
            if (tree.containsKey(subTarget)) {
                sb.append(tree.get(subTarget));
                target = target.substring(endIdx);
                endIdx = 0;
            } else {
                endIdx++;
            }
        }
        System.out.println(sb);
    }
}
