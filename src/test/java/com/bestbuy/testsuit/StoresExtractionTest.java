package com.bestbuy.testsuit;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

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

    //    1. Extract the limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[3].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store  : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> listStorename = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The of all store : " + listStorename);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<String> listOfId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all store id's are : " + listOfId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> listOfData = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + listOfData.size());
        System.out.println("------------------End of Test---------------------------");
    }

//7. Get all the value of the store where store name = St Cloud
@Test
public void test007() {
    List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name ==  'St Cloud'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The size of data list is : " + values);
    System.out.println("------------------End of Test---------------------------");
}

//8. Get the address of the store where store name = Rochester
@Test
public void test008() {
    List<String> storeName = response.extract().path("data.findAll{it.name ==  'Rochester'}.address");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The address of the store where store name is : " + storeName);
    System.out.println("------------------End of Test---------------------------");
}

//9. Get all the services of 8th store
@Test
public void test009() {
    List<HashMap<String,?>>listServices = response.extract().path("data[7].services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The all services of 8th store are : " + listServices);
    System.out.println("------------------End of Test---------------------------");
}

//10. Get storeservices of the store where service name = Windows Store
@Test
public void test010() {
    List<HashMap<String,?>> storeServices = response.extract().path("data.services*.findAll{it.name ==  'Windows Store'}.storeservices");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store where service name is : " + storeServices);
    System.out.println("------------------End of Test---------------------------");
}
//11. Get all the storeId of all the store
@Test
public void test011() {
    List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" :All the storeId of all the store " + storeId);
    System.out.println("------------------End of Test---------------------------");
}
//12. Get id of all the store
@Test
public void test012() {
    List<Integer> listId = response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" :All the storeId of all the store " + listId);
    System.out.println("------------------End of Test---------------------------");
}
//13. Find the store names Where state = ND
@Test
public void test013() {
    List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" :All the storeId of all the store " + storeName);
    System.out.println("------------------End of Test---------------------------");
}
//14. Find the Total number of services for the store where store name = Rochester
@Test
public void test014() {
    List<HashMap<String,?>> totalServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Total number of services for the store where store name is Rochester : "+ totalServices);
    System.out.println("------------------End of Test---------------------------");
}

//15. Find the createdAt for all services whose name = “ WindowsStore”
@Test
public void test015() {
    List<String>allServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("createdAt for all services whose name = “ Windows Store : "+ allServices);
    System.out.println("------------------End of Test---------------------------");
}


//  16. Find the name of all services Where store name = “Fargo”
    @Test
public void test016() {
    List<HashMap<String,?>> allServicesName = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("name of all services Where store name = “Fargo” : "+ allServicesName);
    System.out.println("------------------End of Test---------------------------");
}

//17. Find the zip of all the store
@Test
public void test017() {
    List<Integer> allZip = response.extract().path("data.zip");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All the zip of all the store : " + allZip);
    System.out.println("------------------End of Test---------------------------");
}
//18. Find the zip of store name = Roseville
@Test
public void test018() {
    List<Integer> zipOfStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All the zip of all the store : " + zipOfStore);
    System.out.println("------------------End of Test---------------------------");
}
//19. Find the store services details of the service name = Magnolia Home Theater
@Test
public void test019() {
    List<HashMap<String,?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the store services details of the service name = Magnolia Home Theater : "+ storeServices);
    System.out.println("------------------End of Test---------------------------");
}

//20. Find the lat of all the stores
@Test
public void test020() {
    List<Integer>  allDatalat  = response.extract().path("data.lat");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the lat of all the stores : " +allDatalat);
    System.out.println("------------------End of Test---------------------------");
}
}

