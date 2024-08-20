package models;

import lombok.Data;

import java.util.List;

@Data
public class DataCart {
    List<Integer> items;
    Integer quantity;
}
