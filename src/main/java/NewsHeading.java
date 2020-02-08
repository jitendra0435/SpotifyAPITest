import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class NewsHeading{


    static List<String> listOfWords(List<String> list) {

        List<String> words = new ArrayList<>();

        for (String s1 : list) {
            String[] arrOfWords = s1.split(" ");
            List<String> l1 = Arrays.asList(arrOfWords);
            words.addAll(l1);

        }

        return words;
    }


    public static  String getPopularNewsHeading(Map<String, Integer> mapData, String mostOccuredWord) {
        int value = 0;
        String mostPopular="";
        for (Map.Entry<String, Integer> val : mapData.entrySet()) {
            if (val.getKey().contains(mostOccuredWord )) {
                if ( val.getValue()>value){
                    value=val.getValue();
                    mostPopular=val.getKey();
                }
            }

        }
        System.out.println("Most populaR TEST DATA:"+mostPopular);
        return mostPopular;
    }


    static Map<String, Integer> findMaxCountWord(List<String> wordList) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String i : wordList) {
            Integer j = wordMap.get(i);
            wordMap.put(i, (j == null) ? 1 : j + 1);
        }
        wordMap.entrySet().stream().forEach(System.out::println);
        return wordMap;
    }


    public static String getMaxCountWordInMap(Map<String, Integer> countMap) {

        String key = "";
        Integer value = 0;
        // displaying the occurrence of elements in the arraylist
        for (Map.Entry<String, Integer> val : countMap.entrySet()) {
            if (val.getValue() > value) {
                value = val.getValue();
                key = val.getKey();
            }
            System.out.println("Element " + val.getKey() + " "
                    + "occurs"
                    + ": " + val.getValue() + " times");
        }
        return key;
    }


    public static void main(String[] args) {

        List<String> newsHeadingsList = new ArrayList();
        List<Integer> scorePoints = new ArrayList<>();
/*

        newsHeadingsList.add("Apple is best fruit");
        newsHeadingsList.add("Rajesh is very good boy");
        newsHeadingsList.add("Computer is the most smart device");


        scorePoints.add(20);
        scorePoints.add(24);
        scorePoints.add(34);

*/
        System.setProperty("webdriver.chrome.driver", "/home/admin1/Documents/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://news.ycombinator.com/news");
        List<WebElement> newsElements = driver.findElements(By.cssSelector("a.storylink"));
/*
        List<String> newsHeadings = new ArrayList();
        List<Integer> scorePoints = new ArrayList<>();*/
        List<WebElement> scoreElements = driver.findElements(By.cssSelector("tr td span.score"));
        Map<String, Integer> map = new HashMap<>();

        for (WebElement webElement : newsElements) {
            System.out.println(webElement.getText());
            newsHeadingsList.add(webElement.getText());
        }

        for (WebElement webElement : scoreElements) {
            System.out.println(webElement.getText().substring(0, webElement.getText().length() - 7));
            scorePoints.add(Integer.parseInt(webElement.getText().substring(0, webElement.getText().length() - 7)));
        }


        Map<String, Integer> newsPointMap = new HashMap<>();
        Iterator<String> newsIterator = newsHeadingsList.iterator();
        Iterator<Integer> scorePointIterator = scorePoints.iterator();

        while (newsIterator.hasNext() && scorePointIterator.hasNext()) {

            newsPointMap.put(newsIterator.next(), scorePointIterator.next());

        }
        System.out.println("Data :size" + newsPointMap.size());

        newsPointMap.entrySet().stream().forEach(System.out::println);

        List<String> wordList = listOfWords(newsHeadingsList);

        wordList.stream().forEach(System.out::println);

        Map<String, Integer> maxCountWordMap = findMaxCountWord(wordList);
        String countWordInMap = getMaxCountWordInMap(maxCountWordMap);

        System.out.println("Most Occured word is :" + countWordInMap);
        String popularNewsHeading = getPopularNewsHeading(newsPointMap, countWordInMap);

        System.out.println(popularNewsHeading);
    }
}
