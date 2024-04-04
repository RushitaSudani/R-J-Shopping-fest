package org.technous.validation.request;

import lombok.Getter;
import lombok.Setter;
import org.technous.validation.model.Category;
import org.technous.validation.model.Size;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductRequest {
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int discountPersent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Size> size = new HashSet<>();
    private String imageUrl;
    private String subImage1;
    private String subImage2;
    private String subImage3;
    private Category TopLevelCategory;
    private Category secoundLevelCategory;
    private Category ThirdLevelCategory;

}
