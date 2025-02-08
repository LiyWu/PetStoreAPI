package api.endpoints;

import api.payload.PetPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetEndPoints {
    //Add a new pet to the store
    public static Response addPet(PetPayload petPayload)
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(petPayload)
            .when()
                .post(Routes.postPet_url);

        return response;
    }

    //uploads an image
    public static Response uploadPetImage(int petID, String imgPath)
    {
        Response response = given()
                .pathParam("petId",petID)
                .multiPart("file", imgPath, "image/png")
                .when()
                .post(Routes.postPetImage_url);

        return response;
    }

    //Finds Pets by status
    public static Response findPetByStatus(List<String> petStatus)
    {
        Response response = given()
                .queryParam("status",petStatus)
                .when()
                .get(Routes.findByStatus_url);

        return response;
    }

    //update an existing pet
    public static Response updatePet(PetPayload payload)
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.putPet_url);

        return response;
    }

    //find pet by ID
    public static Response findPetByID(int petID)
    {
        Response response = given()
                .pathParam("petId",petID)
                .when()
                .get(Routes.getPet_url);

        return response;
    }

    //updates a pet in the store with form data
    public static Response updatePetWithFormData(int petID, String name, String status)
    {
        Response response = given()
                .pathParam("petId",petID)
                .contentType(ContentType.URLENC) // Specifies content type as form data
                .formParam("name", name)
                .formParam("status", status)
                .when()
                .post(Routes.updatePet_url);

        return response;
    }

    //deletes a pet
    public static Response deletePet(int petID)
    {
        Response response = given()
                .pathParam("petId", petID)
                .when()
                .delete(Routes.deletePet_url);

        return response;
    }

}
