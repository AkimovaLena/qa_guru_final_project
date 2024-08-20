package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Authors {
    String firstName,lastName,middleName,url;
    Integer id;
    Boolean isForeignAgent;
}
