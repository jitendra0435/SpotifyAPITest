import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpotifyAPI {

    String tokenValue;
    //String iD="k300wijg016de4ddy0e5yme8j";
    String userID;

    @BeforeMethod
    public void setUp() {

        tokenValue = "Bearer BQBKqTXCOKVMvfJSYSBKBqWg4S2V2M7UXX7sf9dXIeKY360L8iIK9R8kImJ6qXJcg-KN268TiAsQ203hpcJqoUw56t2fAtMewhqYUQTh2VSJNvoKAu5mffDa0yxaXbchzp7sfbx6sLpac5vkBOGleu8tz9h8uCYbV3Yf_D59b5p4WyXM80xO27OiYvDroKphBSowCcDXifiexdrE79ZWp4nmsdU5aJYc3Te7JyTdLtWG9wsA-rQDADchVaO4aOvXSXkovZYcA76IwTQMpPY9OaTwGewGEQ";
        RestAssured.baseURI = "https://developer.spotify.com/";

    }

    @Test
    //
    public void givenMethodeFor() throws ParseException {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        JSONObject object = (JSONObject) new JSONParser().parse(response.prettyPrint());
        userID= (String) object.get("id");


    //For varify userID..>>>>>>>>>>>>>>>>

        Response response1 = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me");
        response1.prettyPrint();
        response1.then().assertThat().statusCode(200);
        response1.then().assertThat().body("id",Matchers.equalTo(userID));

    //For total number of playlist>>>>>>>>>>>>

        Response response2 = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me/playlists")
                .then()
                .extract().response();
        int playlistCount = response2.path("total");
        response2.prettyPrint();
        System.out.println("Playlist count: "+playlistCount);


        //Count number of playList  >>>>

        Response response3 = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)

                .body("{\"name\":\"OLD SONGS\",\"description\":\"songs are cool\",\"public\":\"false\"}")
                .when()
                .post("\thttps://api.spotify.com/v1/users/k300wijg016de4ddy0e5yme8j/playlists");
        response3.prettyPrint();
        response3.then().assertThat().statusCode(201);
    }


    @Test
    public void givenMethodForUpdatePlaylist() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)

                .body("{\"name\":\"OLD SONGS\",\"description\":\"Songs Are sleepy\",\"public\":false}")
                .when()
                .put("https://api.spotify.com/v1/playlists/k300wijg016de4ddy0e5yme8j");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }
}