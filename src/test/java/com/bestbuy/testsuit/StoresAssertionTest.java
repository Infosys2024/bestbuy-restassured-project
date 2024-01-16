package com.bestbuy.testsuit;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest  {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
        //response.log().all(); //print logs in console
    }


    //  1. Verify the if the total is equal to 1561
    @Test
    public void test001(){
        response.body("total",equalTo(1561));
    }

    //   2. Verify the if the stores of limit is equal to 10
    @Test
    public void test002(){
        response.body("limit",equalTo(10));
    }
    //   3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void test003(){
        response.body("data.name",hasItem("Inver Grove Heights"));

    }
    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void test004(){
        response.body("data.name",hasItems("Roseville","Burnsville","Maplewood"));
    }
    //5. Verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void test005(){
        response.body("data[2].services[1].storeservices.storeId", equalTo(7));
    }

    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void test006() {
       // response.body("data[2].services[1].storeservices", hasKey("createdAt"));
        response.body("data.findAll{it.name == 'Roseville'}", hasItem(hasEntry("createdAt", "2016-11-17T17:57:05.853Z")));

    }

        //7. Verify the state = MN of forth store
        @Test
        public void verifyState() {
            response.body("data[3].state", equalTo("MN"));

    }
    //8. Verify the store name = Rochester of 9th store
    @Test
    public void verifyStoreNameRochester() {
        response.body("data[8].name", equalTo("Rochester"));
    }

    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void verifyTheStoreId11() {
        response.body("data[5].services[2].storeservices.storeId", equalTo(11));
    }

    //10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyTheServiceId() {
       // response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
        response.body("data[6].services[3].id",equalTo(4));
    }
}