package org.technous.validation.request;

import lombok.Getter;
import lombok.Setter;
import org.technous.validation.model.Sizee;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateProductReq {
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int discountPersent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Sizee> sizee = new HashSet<>();
    private String imageUrl;
    private String subImage1;
    private String subImage2;
    private String subImage3;
    private String TopLevelCategory;
    private String secoundLevelCategory;
    private String ThirdLevelCategory;
}
