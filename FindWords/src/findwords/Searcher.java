package findwords;

import java.util.ArrayList;

/**
 * Your implementation of the coursework.
 * This is the only source file you should modify, and the only one you
 * should submit.  The signatures of these methods should not be modified.
 */
public class Searcher {

    /**
     * Compare the front part of two character arrays for equality.
     *
     * @param s the first character array
     * @param t the second character array
     * @param n number of characters to compare
     * @return true if s and t are equal up to the first n characters
     */
    public boolean equal(String s, String t, int n) {

        if (s.length() < n || t.length() < n) {
            return true;
        }
        // invariant: 0 <= i < n       checks each letter of s and t (i) until i is no longer less than n
        for (int i = 0; i < n; i++) {

            if (s.charAt(i) != t.charAt(i)) {

                return false;

            }
        }
        return true;
    }

    /**
     * Find the first position of a prefix in a dictionary.
     *
     * @param d an ordered dictionary of words
     * @param w a prefix to search for
     * @param n number of characters to compare
     * @return the least index such that all earlier entries in the dictionary
     * are smaller than e when comparing the first n characters.
     */
    public boolean lessThan(String s, String t, int n) {

        if (n > s.length() || n > t.length()){
            if (n > s.length()){
                n = s.length();
            }
            if (n > t.length()){
                n = t.length();
            }
        }
        if (n > s.length()) {
            if (equal(s, t, s.length())) {
                return true;
            }
        }
        // invariant: 0 <= i < n        checks each letter of s and t (i) until i is no longer less than n
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                if (s.charAt(i) == t.charAt(i) ) {
                    if(s.length() < t.length() && n >= s.length() ) {
                        return true;
                    }
                    return false;
                }
            }
            if (s.charAt(i) > t.charAt(i)) {
                return false;
            }
            if (s.charAt(i) < t.charAt(i)) {
                return true;
            }

        }
        return true;
    }

        /**
         * Find the first position of a prefix in a dictionary.
         * @param d an ordered dictionary of words
         * @param w a prefix to search for
         * @param n number of characters to compare
         * @return the least index such that all earlier entries in the dictionary
         * are smaller than e when comparing the first n characters.
         */
        public int findPrefix (Dictionary d, String w,int n){
            int lo = 0;
            int hi = d.size() - 1;
            int mid;
            // invariant: 0 <= lo <= hi + 1 <= n     checks if lo is less than or = to hi and continues looping until lo is greater than hi
            while(lo <= hi) {
                mid = (lo + hi) / 2;
                if(this.lessThan(d.getWord(mid), w, n)) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return lo;
        }


        /**
         * Search a dictionary for words matching a clue.
         * @param d an ordered dictionary of words
         * @param clue a word to search for, with . standing for any character
         * @return a list of all the words in the dictionary that match the clue
         */
        public ArrayList<String> findMatches (Dictionary d, String clue){
            ArrayList<String> matches = new ArrayList<>();

            int n = clue.length();
            int startPos;
            boolean wordMatch;
            // invariant: 0 <= i < clue.length         loops until i is >= than clue.length if charAt(i) == '.' i = n
            for( int i = 0; i < clue.length(); i++) {
                if(clue.charAt(i) == '.') {
                    n = i;
                }
            }

            startPos = this.findPrefix(d, clue, n);
            // invariant: startPos <= i < d.size               loops until i >= than the dictionary size
            for(int i = startPos; i < d.size(); i++) {
                wordMatch = true;
                if(d.getWord(i).length() == clue.length()) {
                    // invariant: 0 <= j < clue.length         loops until j >= the length of the clue
                    for(int j = 0; j < clue.length(); j++) {
                        if(clue.charAt(j) != d.getWord(i).charAt(j) && clue.charAt(j) != '.') {
                            wordMatch = false;
                        }
                    }
                    if(wordMatch) {
                        matches.add(d.getWord(i));
                    }
                }
            }
            return matches;
        }
}




