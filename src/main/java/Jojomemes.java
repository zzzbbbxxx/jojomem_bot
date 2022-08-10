import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Jojomemes {

    public static ArrayList<Mem> mems = new ArrayList<Mem>();

    public static Document openRedditPage(String html){

        Document doc = null;

        try {
            doc = Jsoup.connect(html)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .timeout(35000)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }


    public static void parceRedditJojoSubreddit() {

        Document doc = openRedditPage("https://old.reddit.com/r/JoJoMemes/?limit=100");
        Elements elements_post = doc.getElementsByAttribute("data-fullname");

        do {

            Element element = elements_post.get(elements_post.size() - 1);
            String postId = element.attr("data-fullname");

            for (Element e: elements_post)
                if (isImage(e.attr("data-url"))) {
                    mems.add(new Mem(e.attr("data-url"),
                                     e.attr("data-timestamp"),
                                     e.attr("data-score"),
                                     e.attr("data-permalink")));

                }
            doc = openRedditPage(
                    String.format("https://old.reddit.com/r/JoJoMemes/?limit=100&count=100&after=%s",
                            postId));

            elements_post = doc.getElementsByAttribute("data-fullname");

        } while (!elements_post.isEmpty());



    }

    public static void sortByScore(){

        Collections.sort(mems, new Comparator<Mem>(){
            public int compare(Mem s1, Mem s2) {
                return s1.getDataScore().compareTo(s2.getDataScore());
            }
        });

    }


    public static void sortByDate(){

        Collections.sort(mems, new Comparator<Mem>(){
            public int compare(Mem s1, Mem s2) {
                Long l1 = Long.parseLong(s1.getTimestamp());
                Long l2 = Long.parseLong(s2.getTimestamp());
                return l1.compareTo(l2);
            }
        });

    }

    public static String getNewMem(){

        sortByDate();

        int size = mems.size()-1;
        int halfsize = size / 2;
        int fourth = size / 4;
        int index = randInt(halfsize+fourth,size);

        String link = mems.get(index).getLink();
        mems.remove(mems.get(index));

        if ( size < 200 ) parceRedditJojoSubreddit();

        return link;

    }

    public static String getRandomMemFromBest(){

        sortByScore();

        int halfsize = (mems.size()-1) / 2;
        int index = randInt(halfsize,(mems.size()-1));

        String link = mems.get(index).getLink();
        mems.remove(mems.get(index));

        if ( mems.size()-1 < 200 ) parceRedditJojoSubreddit();

        return link;

    }


    public static int randInt(int min, int max) {

        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;

    }



    public static boolean isImage(String link){

        String png = ".png";
        String jpg = ".jpg";
        String jpeg = "jpeg";
        String gif = ".gif";

        return link.contains(png)
                ||
                link.contains("gfycat")
                ||
                link.contains(jpg)
                ||
                link.contains(jpeg)
                ||
                link.contains(gif);
    }



}
