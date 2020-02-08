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

        tokenValue = "Bearer BQAK13zIZdcBTsxMYns18DF5G21pTRHIcqxQeZPTRpgXy0R9Mm8_bxuJlBnKsrvoaNP8sWPo_IpZz1qw2cx" +
                "_6QX_2F7RPbKvOpD3Vns18IYu6ZF3xh2kLZQMHl0b24cJjwInzkcKbDQFigvett8APAS3-oeBRRbc14XAMA6nB707fMw5E_" +
                "-CXQOy9CVlEd_vaKfM_c9jarSaYbRgYjlZAhp5oRAipRv4rsi_lfEA7h52yh0X-AigQ0Q09WwQZKxCjA1U_dccoCZ8nBv1_H3P8lsWhUOldg";
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
        response5.prettyPrint();
    }
}