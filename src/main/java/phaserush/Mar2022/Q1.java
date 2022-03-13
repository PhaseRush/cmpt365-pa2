package phaserush.Mar2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Q1 {
    private static final Map<String, Integer> dict = new LinkedHashMap<>();

    static {
        dict.put("A", 0);
        dict.put("B", 1);
        dict.put("C", 2);
        dict.put("AB", 3);
        dict.put("BA", 4);
    }

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        var sc = new Scanner(new File(Q1.class.getResource("/Mar2022/q1.txt").toURI()));
        var input = sc.nextLine();
        System.out.println("Entropy:\t" + calcEntropy(input));
        var output = compress(input);
        System.out.println("Compressed:\t" + output);
        System.out.println("Ratio: \t" + ((double)input.length()/output.length()));
        System.out.println("Dictionary:");
        dict.forEach((k, v) -> System.out.println(v + "\t" + k));
    }

    private static double calcEntropy(final String input) {
        var freq = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        return freq.keySet().stream()
                .mapToDouble(ch -> ((double) freq.get(ch)) / input.length())
                .reduce(0d, (acc, curr) -> acc - curr * Math.log(curr) / Math.log(2));
    }

    static String compress(final String input) {
        final StringBuilder output = new StringBuilder();
        int sEnd = 1;
        String s = input.substring(0, sEnd);
        String c;
        while (sEnd < input.length()) {
            c = input.substring(sEnd, sEnd + 1);
            if (dict.containsKey(s + c)) {
                s = s + c;
            } else {
                output.append(dict.get(s));
                dict.put(s + c, dict.size());
                s = c;
            }
            sEnd++;
        }
        output.append(dict.get(s));
        return output.toString();
    }
}
