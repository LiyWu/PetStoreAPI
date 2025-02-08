package api.endpoints;

public class Routes {
    public static String base_url = "https://petstore.swagger.io/v2";

    //Pet
    public static String postPetImage_url = base_url + "/pet/{petId}/uploadImage";
    public static String postPet_url = base_url + "/pet";
    public static String putPet_url = base_url + "/pet";
    public static String getPet_url = base_url + "/pet/{petId}";
    public static String findByStatus_url = base_url + "/pet/findByStatus";
    public static String updatePet_url = base_url + "/pet/{petId}";
    public static String deletePet_url = base_url + "/pet/{petId}";

}
