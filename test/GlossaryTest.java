import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class GlossaryTest {

    @Test
    public void readingTest() {
        Map<String, String> wordDefs = new Map1L<String, String>();
        Map<String, String> wordDefsExpected = new Map1L<String, String>();
        Queue<String> termsExpected = new Queue1L<>();
        SimpleReader input = new SimpleReader1L("test1.txt");
        Queue<String> terms = Glossary.reading(wordDefs, input);
        wordDefsExpected.add("check1", "abc bcd cde");
        termsExpected.enqueue("check1");
        wordDefsExpected.add("check2", "def efg fgh");
        termsExpected.enqueue("check2");
        wordDefsExpected.add("check3", "ghi hij jkl");
        termsExpected.enqueue("check3");
        assertEquals(wordDefsExpected, wordDefs);
        assertEquals(termsExpected, terms);
    }

    @Test
    public void readingTest1() {
        Map<String, String> wordDefs = new Map1L<String, String>();
        Map<String, String> wordDefsExpected = new Map1L<String, String>();
        Queue<String> termsExpected = new Queue1L<>();
        SimpleReader input = new SimpleReader1L("test2.txt");
        Queue<String> terms = Glossary.reading(wordDefs, input);
        wordDefsExpected.add("check1", "Hey");
        termsExpected.enqueue("check1");
        wordDefsExpected.add("check2", "How Are You");
        termsExpected.enqueue("check2");
        wordDefsExpected.add("check3", "All good");
        termsExpected.enqueue("check3");
        assertEquals(wordDefsExpected, wordDefs);
        assertEquals(termsExpected, terms);
    }

    @Test
    public void readingTest2() {
        Map<String, String> wordDefs = new Map1L<String, String>();
        Map<String, String> wordDefsExpected = new Map1L<String, String>();
        Queue<String> termsExpected = new Queue1L<>();
        SimpleReader input = new SimpleReader1L("test3.txt");
        Queue<String> terms = Glossary.reading(wordDefs, input);
        wordDefsExpected.add("check1", " ");
        termsExpected.enqueue("check1");
        wordDefsExpected.add("check2", "1234");
        termsExpected.enqueue("check2");
        wordDefsExpected.add("check3", "Thanks");
        termsExpected.enqueue("check3");
        assertEquals(wordDefsExpected, wordDefs);
        assertEquals(termsExpected, terms);
    }

    @Test
    public void readingTest3() {
        Map<String, String> wordDefs = new Map1L<String, String>();
        Map<String, String> wordDefsExpected = new Map1L<String, String>();
        Queue<String> termsExpected = new Queue1L<>();
        SimpleReader input = new SimpleReader1L("test4.txt");
        Queue<String> terms = Glossary.reading(wordDefs, input);
        wordDefsExpected.add("check1", " A");
        termsExpected.enqueue("check1");
        wordDefsExpected.add("check2", "1234ABCD");
        termsExpected.enqueue("check2");
        wordDefsExpected.add("check3", "AA ");
        termsExpected.enqueue("check3");
        assertEquals(wordDefsExpected, wordDefs);
        assertEquals(termsExpected, terms);
    }

    @Test
    public void readingTest4() {
        Map<String, String> wordDefs = new Map1L<String, String>();
        Map<String, String> wordDefsExpected = new Map1L<String, String>();
        Queue<String> termsExpected = new Queue1L<>();
        SimpleReader input = new SimpleReader1L("test5.txt");
        Queue<String> terms = Glossary.reading(wordDefs, input);
        wordDefsExpected.add("check1", "~ Hey");
        termsExpected.enqueue("check1");
        wordDefsExpected.add("check2", "Nice!");
        termsExpected.enqueue("check2");
        wordDefsExpected.add("check3", "Good.");
        termsExpected.enqueue("check3");
        assertEquals(wordDefsExpected, wordDefs);
        assertEquals(termsExpected, terms);
    }

}
