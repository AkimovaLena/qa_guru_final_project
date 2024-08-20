package models;

import lombok.Data;

@Data
public class DataCatelog {
    int id;
    String type;
    Attributes attributes;
}
