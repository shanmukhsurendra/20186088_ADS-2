import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
/**
 * { function_description }.
 *
 * @param      args  The arguments
 */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash =
             loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    /**
     * { function_description }.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }
    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static
     BinarySearchST<String, Integer> loadDictionary(final String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String,
         Integer>();
        // your code goes here
        String[] arra1 = toReadFile(file);
        int len = arra1.length;
        for (int i = 0; i < len; i++) {
            String word = arra1[i].toLowerCase();
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1);
            } else {
                st.put(word, 1);
            }
        }
        return st;
    }
}

/**
 * Class for t 9.
 */
class T9 {
        /**
         * object for tst.
         */
        private TST tst;
    /**
     * Constructs the object.
     *
     * @param      st    { parameter_description }
     */
    public T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new TST();
        for (String word: st.keys()) {
            tst.put(word, st.get(word));
        }

    }

    /**
     * Gets all words.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }
    /**
     * helper function.
     *
     * @param      digits  The digits
     *
     * @return     { description_of_the_return_value }
     */
    public static ArrayList<String> combinations(final
                             String digits) {
        HashMap<Character, String> map2 = new HashMap<Character, String>();
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        map2.put('2', "abc");
        map2.put('3', "def");
        map2.put('4', "ghi");
        map2.put('5', "jkl");
        map2.put('6', "mno");
        map2.put('7', "pqrs");
        map2.put('8', "tuv");
        map2.put('9', "wxyz");
        list1.add("");

        for (int i = 0; i < digits.length(); i++) {
            for (String str : list1) {
                String letters = map2.get(digits.charAt(i));
                for (int j = 0; j < letters.length(); j++) {
                    list2.add(str + letters.charAt(j));
                }
            }
            list1 = list2;
            list2 = new ArrayList<String>();
        }
        return list1;
    }
    /**
     * gfets the words from numbers.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        Queue<String> possibilities = new Queue<>();
        for (String each: combinations(t9Signature)) {
            if (tst.contains(each)) {
                possibilities.enqueue(each);
            }
        }
        return possibilities;
    }

    /**
     * Gets the suggestions.
     *
     * @param      words  The words
     * @param      k      { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String>
     words, final int k) {
        // your code goes here
        MaxPQ<Integer> mp = new MaxPQ<Integer>();
        TreeSet<String> ts = new TreeSet<String>();
        for (String each : words) {
            // System.out.println("each");
            mp.insert((Integer) tst.get(each));
        }
        for (int i = 0; i < k; i++) {
            int value = mp.delMax();
            //System.out.println("batman here");
            for (String word : words) {
                if (value == (Integer) tst.get(word)) {
                    ts.add(word);
                }
            }
        }
        return ts;
    }

    /**
     * { function_description }.
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
