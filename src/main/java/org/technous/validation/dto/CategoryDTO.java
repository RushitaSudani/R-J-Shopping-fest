package org.technous.validation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    //private int categoryid;
    @NotEmpty(message = "not be empty")
    @Size(min = 3, max = 20, message = "categories 3 to 20")
    private String categorytitle;
    @NotEmpty(message = "description not be empty")
    @Size(min = 3, max = 20, message = "description must between 3 to 20")
    private String categorydescription;
}
