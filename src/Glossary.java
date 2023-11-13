import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to receive a text file of different words and to output an html page
 * with an index table of words linked with each other inside the file.
 *
 * @author Gautam Agarwal
 *
 */

public final class Glossary {

    /**
     * Compare @code String s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    /**
     * No argument constructor.
     */
    private Glossary() {

    }

    /**
     * Extract the terms and definitions from the input file and finally return
     * a queue containing the terms.
     *
     * @param wordMap
     *            It's a map of strings used from terms and its definitions.
     * @param in
     *            input the text file from the user
     * @return the queue containing the terms
     * @requires Map is vacant in is arranged as term then definition on after
     *           lines and clear space in the middle of the meaning of a word
     *           and the following term
     * @ensures Modification of the map to have a term as the key and its
     *          definition as value
     */
    public static Queue<String> reading(Map<String, String> wordMap,
            SimpleReader in) {
        Queue<String> terms = new Queue1L<>();

        while (!in.atEOS()) {
            String word = in.nextLine();
            String definition = in.nextLine();
            String temp = " ";

            while (!in.atEOS() && temp.length() > 0) {
                temp = in.nextLine();
                if (temp.length() > 0) {
                    definition = definition.concat(temp);
                }
            }
            wordMap.add(word, definition);
            terms.enqueue(word);
        }
        return terms;
    }

    /**
     * Reads the map to produce link for every available term provided.
     *
     * @param wordMap
     *            It's a map of strings used from terms and its definitions.
     * @param terms
     *            queue containing the terms
     * @param out
     *            used for output and to store the created html files
     * @requires wordMap or its terms should not be empty.
     * @ensures nothing is modified
     */
    public static void generateLink(Map<String, String> wordMap,
            Queue<String> terms, String out) {
        Queue<String> temp = new Queue1L<>();

        while (terms.length() > 0) {
            String word = terms.dequeue();
            String def = wordMap.value(word);
            temp.enqueue(word);

            SimpleWriter link = new SimpleWriter1L(out + "/" + word + ".html");
            link.println("<html>");
            link.println("<head>");
            link.println("<title>" + word + "</title>");
            link.println("</head>");
            link.println("<body>");
            link.println("<h2><b><i><font color=\"red\">" + word
                    + "</font></i></b></h2>");
            link.println("<blockquote>" + def + "</blockquote>");
            link.println("<hr />");
            link.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
            link.println("</body>");
            link.println("</html>");
            link.close();
        }
        terms.transferFrom(temp);
    }

    /**
     * Takes a string and searches for the primary word beginning at the pos.
     * When the strategy tracks down a separator, it decides the word is the
     * beginning situation until just before the separator. The word is
     * returned. If the pos is at a separator, the separator alone is returned.
     *
     * @param text
     *            Inspects the words
     * @param pos
     *            Checks word pos
     * @param separators
     *            Set designing separators.
     * @return Word or separator if the pos starts at one
     * @requires text and separators to be non empty and also positions to be
     *           less than the text length.
     */
    public static String next(String text, int pos, Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= pos : "Violation of: 0 <= pos";
        assert pos < text.length() : "Violation of: pos < |text|";

        final int end = -2;
        int ePos = end;
        String word = " ";

        for (int n = pos; n < text.length(); n++) {
            if (separators.contains(text.charAt(n)) && ePos == end) {
                ePos = n;
            }
        }

        if (ePos == end) {
            word = text.substring(pos);
        } else if (ePos == pos) {
            word = text.substring(pos, ePos + 1);
        } else {
            word = text.substring(pos, ePos);
        }
        return word;
    }

    /**
     * Modifies the definition so that the words or terms present in the
     * definitions are linked to each other directly.
     *
     * @param wordMap
     *            It's a map of strings used from terms and its definitions.
     * @param terms
     *            queue containing the terms
     * @param out
     *            used for output and to store the created html files
     * @requires wordMap or its terms should not be empty.
     * @ensures nothing is modified created in the function generateLinks
     */
    public static void modification(Map<String, String> wordMap,
            Queue<String> terms, String out) {
        Queue<String> temp = new Queue1L<>();
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('.');
        separators.add(',');
        separators.add('/');
        separators.add(':');
        separators.add(';');
        separators.add('\'');
        separators.add('?');
        separators.add('!');

        int pos = 0;
        while (terms.length() > 0) {
            String term = terms.dequeue();
            temp.enqueue(term);
            String definition = wordMap.value(term);
            String finalDefinition = " ";

            while (pos < definition.length()) {
                String word = next(definition, pos, separators);

                if (wordMap.hasKey(word)) {
                    finalDefinition += "<a href=\"" + word + ".html\">" + word
                            + "</a>";
                } else {
                    finalDefinition = finalDefinition.concat(word);
                }
                pos = pos + word.length();
            }
            wordMap.replaceValue(term, finalDefinition.substring(1));
            pos = 0;
        }
        terms.transferFrom(temp);
    }

    /**
     * Main method used for taking the user input and alo generating the output
     * in the user desired output folder location creating the required html
     * files linked with each other as required.
     *
     * @requires The input file must contains different term and their
     *           definitions.
     *
     * @param args
     *            Command line arguments not used
     */
    public static void main(String[] args) {

        SimpleReader userInput = new SimpleReader1L();
        SimpleWriter userOutput = new SimpleWriter1L();
        userOutput.print("Enter input file: ");
        String input = userInput.nextLine();
        userOutput.print("Enter folder where output files will be saved: ");
        String output = userInput.nextLine();

        SimpleReader in = new SimpleReader1L(input);
        SimpleWriter out = new SimpleWriter1L(output + "/index.html");
        Map<String, String> wordMap = new Map1L<>();
        Queue<String> terms = new Queue1L<>();

        terms.append(reading(wordMap, in));
        Comparator<String> cs = new StringLT();
        terms.sort(cs);

        modification(wordMap, terms, output);
        generateLink(wordMap, terms, output);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Glossary</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Glossary</h2>");
        out.println("<hr />");
        out.println("<h3>Index</h3>");
        out.println("<ul>");

        while (terms.length() > 0) {
            String term = terms.dequeue();
            out.println(
                    "<li><a href=\"" + term + ".html\">" + term + "</a></li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        userInput.close();
        userOutput.close();
        in.close();
        out.close();
    }
}
