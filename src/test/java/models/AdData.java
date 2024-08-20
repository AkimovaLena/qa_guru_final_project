package models;

import lombok.Data;

import java.util.List;

@Data
public class DataShort {
    List<Integer> items;
    Integer quantity;
}
