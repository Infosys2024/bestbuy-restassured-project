package com.bestbuy.testsuit;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
        //response.log().all(); //print logs in console
    }
    //21. Extract the limit
        @Test
        public void test021() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }
    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }
//23. Extract the name of 5th product
@Test
public void test023() {
    String productName = response.extract().path("data[3].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The name of 5th product : " + productName);
    System.out.println("------------------End of Test---------------------------");
}
//24. Extract the names of all the products
@Test
public void test024() {
    List<String> listOfProductname = response.extract().path("data.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The names of all the products : " + listOfProductname);
    System.out.println("------------------End of Test---------------------------");
}
//25. Extract the productId of all the products
@Test
public void test005() {
    List<Integer> listOfId = response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("the productId of all the products : " + listOfId);
    System.out.println("------------------End of Test---------------------------");
}

//26. Print the size of the data list
@Test
public void test026() {
    List<Integer> listOfData = response.extract().path("data");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Print the size of the data list : " + listOfData.size());
    System.out.println("------------------End of Test---------------------------");
}
//27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
@Test
public void test027() {
    List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name ==  'Energizer - MAX Batteries AA (4-Pack)'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of the product where product name = Energizer - MAX Batteries AA (4-Pack) : " + values);
    System.out.println("------------------End of Test---------------------------");
}
//28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
@Test
public void test028() {
    List<String> storeName = response.extract().path("data.findAll{it.name ==  'Energizer - N Cell E90 Batteries (2-Pack)'}.address");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" the product where product name = Energizer - N Cell E90 Batteries (2-Pack) : " + storeName);
    System.out.println("------------------End of Test---------------------------");
}
//29. Get all the categories of 8th products
@Test
public void test029() {
    List<HashMap<String,?>>listServices = response.extract().path("data[7].services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The all services of 8th store are : " + listServices);
    System.out.println("------------------End of Test---------------------------");
}
//30. Get categories of the store where product id = 150115
@Test
public void test030() {
    List<HashMap<String,?>> categories = response.extract().path("data.findAll{it.id==150115}.categories");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" the store where product id = 150115: " + categories);
    System.out.println("------------------End of Test---------------------------");
}
//31. Get all the descriptions of all the products
@Test
public void test031() {
    List<String> descriptions = response.extract().path("data.descriptions");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" the descriptions of all the products: " + descriptions);
    System.out.println("------------------End of Test---------------------------");
}
//32. Get id of all the all categories of all the products
@Test
public void test032() {
    List<Integer> listId = response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" The all categories of all the products : " + listId);
    System.out.println("------------------End of Test---------------------------");
}
//33. Find the product names Where type = HardGood
@Test
public void test033() {
    List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the product names Where type = HardGood : " + productName);
    System.out.println("------------------End of Test---------------------------");
}
//34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
@Test
public void test034() {
    List<HashMap<String,?>> categories = response.extract().path("data.findAll{it.name == ' Duracell - AA 1.5V CopperTop Batteries (4-Pack'}.categories");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) : "+ categories);
    System.out.println("------------------End of Test---------------------------");
}
//35. Find the createdAt for all products whose price < 5.49
@Test
public void test035() {
    List<String> createdAt = response.extract().path("data.findAll{it.price<5.49}.createdAt");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the createdAt for all products whose price < 5.49 : "+ createdAt);
    System.out.println("------------------End of Test---------------------------");
}
//  36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
@Test
public void test036() {
    List<String> name = response.extract().path("data.findAll{it.name == ' Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”: "+ name);
    System.out.println("------------------End of Test---------------------------");
}
//37. Find the manufacturer of all the products
@Test
public void test037() {
    List<String> manufacturer = response.extract().path("data.manufacturer");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the manufacturer of all the products : " + manufacturer);
    System.out.println("------------------End of Test---------------------------");
}
//38. Find the img of products whose manufacturer is = Energizer
@Test
public void test038() {
    List<HashMap<String, ?>> img = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("nd the img of products whose manufacturer is = Energizer : " + img);
    System.out.println("------------------End of Test---------------------------");
}
//39. Find the createdAt for all categories products whose price > 5.99
@Test
public void test039() {
    List<String> createdAt = response.extract().path("data.findAll{it.price > 5.49}.createdAt");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" Find the createdAt for all categories products whose price > 5.99 : "+ createdAt);
    System.out.println("------------------End of Test---------------------------");
}

//40. Find the url of all the products
@Test
public void test040() {
    List<HashMap<String, ?>> url = response.extract().path("data.url");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the url of all the products : " +url);
    System.out.println("------------------End of Test---------------------------");
}

}
