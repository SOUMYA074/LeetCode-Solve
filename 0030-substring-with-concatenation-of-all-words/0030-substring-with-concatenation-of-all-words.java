import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();

        int w = words[0].length();
        int k = words.length;
        int total = w * k;

        if (s.length() < total) return ans;

        Map<String, Integer> need = new HashMap<>();

        for (String word : words) {
            need.merge(word, 1, Integer::sum);
        }

        for (int offset = 0; offset < w; offset++) {
            int left = offset;
            int count = 0;

            Map<String, Integer> window = new HashMap<>();

            for (int right = offset; right + w <= s.length(); right += w) {
                String word = s.substring(right, right + w);

                // Word isn't required → reset window
                if (!need.containsKey(word)) {
                    window.clear();
                    count = 0;
                    left = right + w;
                    continue;
                }

                window.merge(word, 1, Integer::sum);
                count++;

                // Too many occurrences of this word
                while (window.get(word) > need.get(word)) {
                    String leftWord = s.substring(left, left + w);
                    window.put(leftWord, window.get(leftWord) - 1);
                    left += w;
                    count--;
                }

                // Exactly k words
                if (count == k) {
                    ans.add(left);

                    // Move forward to search for next window
                    String leftWord = s.substring(left, left + w);
                    window.put(leftWord, window.get(leftWord) - 1);
                    left += w;
                    count--;
                }
            }
        }

        return ans;
    }
}