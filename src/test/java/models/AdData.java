package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdData {
    @JsonProperty("item_list_name")
    String itemListName;
    @JsonProperty("product_shelf")
    String productShelf;
}
