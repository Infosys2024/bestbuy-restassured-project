package com.bestbuy.crudtest;

import com.bestbuy.models.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase {
    static String name = "Jalpa" + TestUtils.getRandomValue();
    static String type = "Testing";
    static int price = 700;
    static int shipping = 1;
    static String upc = "1324567";
    static String description = "API";
    static String manufacturer = "Duracell";
    static String model = "Testing";
    static String url = "bestbuy" + TestUtils.getRandomValue() + "@gmail.com";
    static String image = "prime.jpg";

    static int productID;


    @Test
    public void test001() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

        productID = response.then().extract().path("id");
        System.out.println("ID = " + productID);

    }
    @Test
    public void test002() {

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productID);
        response.then().statusCode(200);

        response.prettyPrint();
    }


    //Get Data
    @Test
    public void test003() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_123");
        productPojo.setType(type + "_good");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productPojo)
                .when()
                .patch("/" + productID);
        response.then().statusCode(200);

        response.prettyPrint();
    }

    //Delete Data
    @Test
    public void test004() {

        given()
                .when()
                .delete("/" + productID)
                .then()
                .statusCode(200);

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productID);
        response.then().statusCode(404);
    }
}

