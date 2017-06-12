package at.tuwien.innovation.group7;

import java.awt.print.Book;
import java.util.*;

/**
 * Created by xxx on 6/11/17.
 */
public class TestCss {
    public static void main(String [] args){

        String test = "Liebesgeschichten [und Heiratssachen] : [Posse mit Gesang in 3 Aufzügen]";
        String test2 = " [Musik v. Michael Hebenstreit. Von Joh. Nestroy],[Erstaufführung 23.3.1843 Theater a.d. Wien]; Rollenheft (Singstimme [Baß] mit Text u. tlw. Instr.-Melodie) für: [?],Bearbeiter: he";

        System.out.println(makeItNice(test2));

    }

    public static Collection<String> makeItNice(String in){
        in = in.replaceAll("[^A-Za-z0-9]", " ");
        String in2 = in.trim().replaceAll(" +", " ");

        String [] wordArr = in2.split(" ");

        List<String> bookWords = Arrays.asList(wordArr);
        Iterator<String> bookWordsIter = bookWords.iterator();

        Collection<String> coll = new ArrayList<String>(bookWords);
        //populate

        // remove single characters
        coll.removeIf(word -> word.length() < 3);

        System.out.println(coll);

        return coll;
    }
}
