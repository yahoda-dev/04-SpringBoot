package org.example.jpaintro.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMenuStatisticsDTO {
    private Integer categoryCode;
    private String categoryName;
    private Long menuCount;
    private Double averagePrice;
}
