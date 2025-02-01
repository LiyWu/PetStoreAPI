package api.test;

import api.endpoints.PetEndPoints;
import api.payload.PetPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PetTests {
    PetPayload petPayload;
    @BeforeClass
    public void setupData()
    {
        try {
            Path path = Paths.get("src/test/java/api/data/pet/pet_demo1.json");
            ObjectMapper objectMapper = new ObjectMapper();
            petPayload = objectMapper.readValue(path.toFile(), PetPayload.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void addPet()
    {
        Response response = PetEndPoints.addPet(petPayload);
        response.then().log().all();
        // Check status code
        Assert.assertEquals(response.getStatusCode(),200);
        // Check 'id' field
        Assert.assertEquals(response.jsonPath().getInt("id"),petPayload.getId());
        // Check 'name' field
        Assert.assertEquals(response.jsonPath().getString("name"), petPayload.getName());
        // Check 'status' field
        Assert.assertEquals(response.jsonPath().getString("status"), petPayload.getStatus());
    }
    @Test
    public void findPetByID()
    {
        Response response = PetEndPoints.findPetByID(petPayload.getId());
        response.then().log().all();

        // Check status code
        Assert.assertEquals(response.getStatusCode(),200);
        // Check 'id' field
        Assert.assertEquals(response.jsonPath().getInt("id"),petPayload.getId());

    }

    @Test
    public void findPetByStatus()
    {
        //List<String> statuses = Arrays.asList("available", "pending", "sold");
        List<String> statuses = Arrays.asList("sold");

        Response response = PetEndPoints.findPetByStatus(statuses);

        // don't print, too many results
        //response.then().log().all();
        // Check status code
        Assert.assertEquals(response.getStatusCode(),200);
        // Check status of first result
        Assert.assertEquals(response.jsonPath().getString("status[0]"),statuses.get(0));
    }

    @Test
    public void uploadImage()
    {
        Response response = PetEndPoints.uploadPetImage(petPayload.getId(), "api/data/pet/puppy1.jpeg");
        response.then().log().all();
        // Check status code
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void updatePet()
    {
        String newName = "ChangedPuppyName";
        petPayload.setName(newName);
        Response response = PetEndPoints.updatePet(petPayload);
        response.then().log().all();
        // Check status code
        Assert.assertEquals(response.getStatusCode(),200);
        // Check updated pet name
        Assert.assertEquals(response.jsonPath().getString("name"), newName);
    }

    @Test
    public void updatePetWithFormData()
    {
        String newName = "newName2";
        String newStatus = "sold";
        Response response = PetEndPoints.updatePetWithFormData(petPayload.getId(), newName, newStatus);
        response.then().log().all();
        // Check status code
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void deletePet()
    {
        Response response = PetEndPoints.deletePet(petPayload.getId());
        response.then().log().all();
        // Check status code
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
