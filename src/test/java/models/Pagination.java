package models;

import lombok.Data;

@Data
public class Pagination {
    int count, current_page, per_page, total, total_pages;
}
