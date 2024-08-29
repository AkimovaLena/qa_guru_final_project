package models;

import lombok.Data;

import java.util.List;

@Data
public class ResponseCatalog {
    List<DataCatalog> data;
    Meta meta;

}
