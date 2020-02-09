import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpotifyAPI {

    String tokenValue;
    String userID;
    String playListID;

    @BeforeMethod
    public void setUp() {

        tokenValue = "Bearer BQBU2YpzjgID2ueSRLSgam4oPbCKLlc0cc48-KmFD2qKAqHF0aMA3oSPCMqd5vG3jt_slRMOCgmtMpCqORVXm8gPbMsKV4e4JwkY25mSFi-4hcg2zvFNk308mcb6XKhKAXQ7DhlPMSe9M3wQUASnD4K96z5bdOhe7Tp2HdAMSDPueyYzQUQweygbYFo6IwizSnE1AeehtK7vcDiGuM10FhyBPYXgQZ1odMbuDyPQFhN_Ijae2VQf7RWMOtYGoRqrwayRmboragrkkmKGpvxbxSDTkBGwkg";
        RestAssured.baseURI = "https://developer.spotify.com/";

    }

    @Test
    public void givenMethodeForSpotifyAPI() throws ParseException {

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.then().assertThat().statusCode(200);
        JSONObject object = (JSONObject) new JSONParser().parse(response.prettyPrint());
        userID = (String) object.get("id");

        /*..................................................................................................*/

        //For varify userID
        Response response1 = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me");
        response1.then().assertThat().statusCode(200);
        response1.then().assertThat().body("id", Matchers.equalTo(userID));

     /*..................................................................................................*/

        //For total number of playlist
        Response response2 = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me/playlists")
                .then()
                .extract().response();
        int playlistCount = response2.path("total");
        System.out.println("========="+playlistCount);
        playListID=response2.path("items[1].id");
        response2.prettyPrint();

        /*..................................................................................................*/

        //Creating playlist
        Response response3 = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)

                .body("{\"name\":\"Rap songs\",\"description\":\"songs are cool\",\"public\":\"false\"}")
                .when()
                .post("\thttps://api.spotify.com/v1/users/" + userID + "/playlists");

        response3.then().assertThat().statusCode(201);

        /*..................................................................................................*/

       //for Updating the playList Details
        Response response5 =RestAssured. given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization",tokenValue)
                .body("{\"name\":\"Marathi songs\",\"description\":\"songs are good\",\"public\":false}")
                .when()

                .put("https://api.spotify.com/v1/playlists/"+playListID)
                .then()
                .extract().response();
        //response5.then().assertThat().body("total",Matchers.equalTo(playlistCount));
        response5.prettyPrint();
    }
}