package models;

import lombok.Data;

@Data
public class DataCatalog {
    int id;
    String type;
    Attributes attributes;
}
