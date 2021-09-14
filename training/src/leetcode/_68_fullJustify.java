package leetcode;

import java.util.ArrayList;
import java.util.List;

public class _68_fullJustify {
    public static void main(String[] args) {
        String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        System.out.println(fullJustify(words, 20));
    }
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int l = 0, r = 0, len = 0;
        StringBuffer sb = new StringBuffer();
        while (r < words.length) {
            if (len + words[r].length() <= maxWidth) {
                len += words[r].length() + 1;
                r++;
            } else {
                int num = maxWidth - len + 1;
                int d = r - l - 1;
                if (d == 0) {
                    sb.append(words[l]);
                    sb.append(" ".repeat(num));
                    l++;
                } else {
                    int i = l;
                    while (i < r) {
                        sb.append(words[i]);
                        if (i != r - 1) {
                            sb.append(" ".repeat(num / d + 1));
                            if (i - l < num % d) {
                                sb.append(" ");
                            }
                        }
                        i++;
                    }
                    l = r;
                }
                res.add(sb.toString());
                len = 0;
                sb.delete(0, sb.length());
            }
        }
        int num = maxWidth - len + 1;
        while (l < r) {
            sb.append(words[l]);
            if (l != r - 1) {
                sb.append(" ");
            }
            l++;
        }
        sb.append(" ".repeat(num));
        res.add(sb.toString());
        return res;
    }
}
