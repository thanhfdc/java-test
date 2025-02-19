package com.metasol.dto.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.metasol.entity.TypeEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearchDto extends SearchDto{

    private String name;

    @JsonProperty("phone_number")
    private Long phoneNumber;

    @JsonProperty(value = "is_active", defaultValue = "true")
    private boolean isActive;

    @JsonProperty("type_id")
    private TypeEntity typeId;
}
