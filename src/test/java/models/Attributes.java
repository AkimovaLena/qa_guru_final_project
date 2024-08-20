package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Attributes {
    List<Authors> authors;
    Boolean bestseller, exclusive,inSubscription,isBook,isBookmarks,recommended,sale,saleSoon;
    @JsonProperty("new")
    Boolean newCatalog;
    String binding, code, description, discount, originalPicture, pages, picture, preOrderDate,startSale,startSaleDesc,status,title,url;
    Category category;
    List<String> categoryChain, coauthors;
    int id, oldPrice, price, yearPublishing;
    Publisher publisher;
    Rating rating;
}
