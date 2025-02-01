package api.payload;

import lombok.Data;
import java.util.List;

@Data
public class PetPayload {
    private int id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;
}

@Data
class Category {
    private int id;
    private String name;
}

@Data
class Tag {
    private int id;
    private String name;
}
