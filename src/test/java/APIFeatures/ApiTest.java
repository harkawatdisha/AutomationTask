package APIFeatures;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ApiTest {
    @Test()
    public void apiTest(){
       Response startShipResponse = null;
        Response pilotResponse= null;
       Response response= RestAssured.get("https://swapi.dev/api//films");
       //response.prettyPrint();
       ResponseBody body = response.getBody();
       String bodyAsString = body.asString();
       Assert.assertTrue(bodyAsString.contains("A New Hope"));
       String responseStar =response.jsonPath().getString("results[0].starships");
       List<String> starshipsApi = List.of(responseStar.substring(1).split(","));
       for( String starship: starshipsApi)
           startShipResponse= RestAssured.get(starship);
       if(startShipResponse.getBody().asString().contains("Biggs Darklighter")) {
           String starShip = startShipResponse.jsonPath().getString("name");
           System.out.println(starShip);

           if(startShipResponse.jsonPath().getString("starship_class").equals("Starfighter"))
           {
               System.out.println("starship class is starfighter");
           }
           else
               System.out.println("starship class is other fighter");
           }
        String responsePilot =startShipResponse.jsonPath().getString("pilots");
        List<String> pilotsList = List.of(responsePilot.substring(1).split(","));
        for( String starPilot: pilotsList)
             pilotResponse= RestAssured.get(starPilot);
        if(pilotResponse.getBody().asString().contains("Luke Skywalker")) {
            System.out.println("Pilot is Luke Skywalker");
        }
        else
            System.out.println("Pilot is other person");
       }
    }

