package org.technous.validation.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse {
    private String message;
    private boolean Status;

    //private Object object;
}
