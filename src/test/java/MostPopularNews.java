import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.*;

public class MostPopularNews {

     @Test
   public void givenMethodForReturningNewsWhichIsIsPopular() {
//
//        System.setProperty("webdriver.chrome.driver", "/home/admin1/IdeaProjects/PowerFunction/driver/chromedriver");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://news.ycombinator.com/");
//
//        List<WebElement> news = driver.findElements(By.cssSelector("tr.athing a.storylink"));
//        List<WebElement> score = driver.findElements(By.cssSelector("tr span.score"));
//
//        List<String> newsHeading = new ArrayList<String>();
//        List<String> scoreHeading = new ArrayList<String>();
//
//        for (WebElement n : news) {
//            newsHeading.add(n.getText());
//        }
//        for (WebElement s : score) {
//            scoreHeading.add(s.getText().substring(0, s.getText().length() - 7));
//        }
//        Map<String, Integer> map = new HashMap<String, Integer>();
//
//        for (int i = 0; i < scoreHeading.size(); i++) {
//            map.put(newsHeading.get(i), Integer.parseInt(scoreHeading.get(i)));
//
//        }
//
//
//        System.out.println(newsHeading);
//        System.out.println(scoreHeading);
//        listOfWord(newsHeading);
//        //  countWord(newsHeading);
//        driver.close();
//
//    }
//
//         static List<String> listOfWord (List < String > newsHeading) {
//             List<String> listOfWords = new ArrayList<>();
//             for (String words : newsHeading) {
//                 String[] arrOfString = words.split(" ");
//                 String world = findWord(arrOfString);
//
//                 List<String> l1 = Arrays.asList(arrOfString);
//                 listOfWords.addAll(l1);
//
//                 System.out.println(world);
//             }
//             // System.out.println(listOfWords);
//
//             return listOfWords;
//         }

         //
//    static List<String> countWord(List<String> count){
//        HashMap<String,Integer> sc=new HashMap<>();
//        List<String> abc = listOfWord(count);
//        System.out.println("new list");
//        System.out.println(abc);
//        return abc;
//    }
//
//         static String findWord (String[]arr){
//
//             // Create HashMap to store word and it's frequency
//             HashMap<String, Integer> hs = new HashMap<String, Integer>();
//
//             // Iterate through array of words
//             for (int i = 0; i < arr.length; i++) {
//                 // If word already exist in HashMap then increase it's count by 1
//                 if (hs.containsKey(arr[i])) {
//                     hs.put(arr[i], hs.get(arr[i]) + 1);
//                 }
//                 // Otherwise add word to HashMap
//                 else {
//                     hs.put(arr[i], 1);
//                 }
//             }
//
//             // Create set to iterate over HashMap
//             Set<Map.Entry<String, Integer>> set = hs.entrySet();
//             String key = "";
//             int value = 0;
//
//             for (Map.Entry<String, Integer> me : set) {
//                 // Check for word having highest frequency
//                 if (me.getValue() > value) {
//                     value = me.getValue();
//                     key = me.getKey();
//                 }
//             }
//             // Return word having highest frequency
//             System.out.println(key);
//             return key;
//         }

             List<String> newsHeadings = new ArrayList<String>();

             newsHeadings.add("Trump Travels to india");
             newsHeadings.add("Modi goes to china");
             newsHeadings.add("china affected by corona badly");
             newsHeadings.add("Today is election in delhi modi is there");
             newsHeadings.add("congress approch name of rahul of PM");

             for (int i = 0; i < newsHeadings.size(); i++) {
                 System.out.println(newsHeadings.get(i));
             }


                 List<Integer> scoreHeadings = new ArrayList<>();
                 scoreHeadings.add(52);
                 scoreHeadings.add(100);
                 scoreHeadings.add(200);
                 scoreHeadings.add(500);
                 scoreHeadings.add(150);

                 for (int j = 0; j < scoreHeadings.size(); j++) {
                     System.out.println(scoreHeadings.get(j));
                 }

             }

         }





