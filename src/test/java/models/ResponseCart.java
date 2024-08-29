package models;

import lombok.Data;

import java.util.List;

@Data
public class ResponseCart {
    Integer addBonuses, cost, costGiftWrap, costWithBonuses, costWithSale, discount, weight;
    String promoCode;
    List<Integer> disabledProducts, gifts, preorderProducts;
    List<Products> products;
}
