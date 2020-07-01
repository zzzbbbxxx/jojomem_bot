import org.checkerframework.checker.units.qual.A;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class jojomemes {

    public static ArrayList<String> memlinks = new ArrayList<String>();

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


    public void parceRedditJojoSubreddit() {

        String html = "https://old.reddit.com/r/JoJoMemes/?limit=100";
        Document doc = openRedditPage(html);
        Elements elements_post = doc.getElementsByAttribute("data-fullname");

        Element element = elements_post.get(elements_post.size() - 1);
        String postId = element.attr("data-fullname");
        String oldPostId = "";


        System.out.println("this is first postId: " + postId);

        while (!postId.equals(oldPostId))
        {
            for (Element e: elements_post)
            {
                    if (imgCheck(e.attr("data-url")))
                    {
                        memlinks.add(e.attr("data-url"));
                    }
            }

            html=String.format("https://old.reddit.com/r/JoJoMemes/?limit=100&count=100&after=%s", postId);
            oldPostId=postId;
            doc = openRedditPage(html);
            elements_post = doc.getElementsByAttribute("data-fullname");
            element = elements_post.get(elements_post.size() - 1);
            postId = element.attr("data-fullname");

            if (postId.equals(oldPostId))
                break;

            System.out.println("this is first postId: " + postId);


        }
        System.out.println("this is first postId: " + postId);

        System.out.println(memlinks);


    }


    public String getRandomRedditPage(String html){

        Document doc = openRedditPage(html);
        Elements elements = doc.getElementsByAttribute("data-fullname");
        Element element = randElements(elements);
        String postId = element.attr("data-fullname");
        System.out.println("this is first postId: " + postId);


        for (int i=0; i<=randInt(1,5); i++) {
            html=String.format("https://old.reddit.com/r/JoJoMemes/?limit=100&count=100&after=%s", postId);
            doc = openRedditPage(html);
            elements = doc.getElementsByAttribute("data-fullname");
            element = randElements(elements);
            postId = element.attr("data-fullname");
            System.out.println("this is postId from random: " + postId);
        }
        return html;
    }


    public String getRandomMemLink(){

        String link = "";

        System.out.println(memlinks);

        if (memlinks.isEmpty())
        {
            String html = getRandomRedditPage("https://old.reddit.com/r/JoJoMemes");
            Document doc = openRedditPage(html);
            Elements elements = doc.getElementsByAttribute("data-url");

            while (elements.isEmpty()) {
                html = getRandomRedditPage("https://old.reddit.com/r/JoJoMemes");
                doc = openRedditPage(html);
                elements = doc.getElementsByAttribute("data-url");
            }

            for (Element element : elements) {

                element = randElements(elements);
                if (imgCheck(element.attr("data-url"))) {
                    link = element.attr("data-url");
                    break;
                }

                System.out.println("this is link url from parce_now: " + link);

            }
        } else
            {
                System.out.println("this is link url from list: " + link);
                int size = memlinks.size()-1;
                link = memlinks.get(randInt(size,size));
                memlinks.remove(size);
            }

        return link;

    }



    public static Element randElements(Elements elements) {

        Random rand = new Random();
        return elements.get(rand.nextInt(elements.size()));
    }


    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }



    public static boolean imgCheck(String http){
        String png = ".png";
        String jpg = ".jpg";
        String jpeg = "jpeg"; // no period so checker will only check last four characaters
        String gif = ".gif";
        int length = http.length();

        if (http.contains(png)|| http.contains("gfycat") || http.contains(jpg)|| http.contains(jpeg) || http.contains(gif)){
            return true;
        }
        else{
            return false;
        }
    }


    public static void main(String[] args)
    {
        System.out.println("begin/");
        jojomemes d= new jojomemes();
        d.getRandomMemLink();
    }

}
