package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Products {
    AdData adData;
    List<Authors> authors;
    Category category;
    List<String> categoryChain;
    List<String> coauthors;
    int cost, fullCost, fullPrice, goodsId, id, price, quantity, stock, weight;
    Boolean disabledBonuses, inSubscription, isBook, isBookmarks, isMagic, preOrder, sale;
    String picture, publisher, status, title, url;
    @JsonProperty("nForM")
    private NForM nForM;
}
