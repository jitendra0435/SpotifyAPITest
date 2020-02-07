import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpotifyAPIMAIN {

    String tokenValue;
    String iD="k300wijg016de4ddy0e5yme8j";

    @BeforeMethod
    public void setUp() {

        tokenValue = "Bearer BQBhhUJxo-74-SPSkKA6HCZr_mMgMf6B2VBirL-3Hie4MXzcThsb7pql3gECN4qbunB4sZdNiTcJmIi5AxPyVoVE1IoOm2rgbkmdQz6eKULO-Ett0PKSQMwRBg5FRFYs8T9CBnEAS4XfdDSmmutrd4XQqzunQV8jVreLb2gHQL7hJE7aeuKGjkomwZXhPBSMR02ql_RIsn7-VSuIDB-zLb6pRVCjEPKknvYaKDuZFA9Buqhqoe6i_kn-5xlHAnT71p9g4AijwDW4m6A5u6Hfnbl_iDUrzw";
        RestAssured.baseURI = "https://developer.spotify.com/";

    }

    @Test
    public void givenMethodeFor() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
}
