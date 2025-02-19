package com.metasol.dto.response;

import jakarta.persistence.JoinColumn;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeResponseDto {
    @JoinColumn(name = "type_name")
    private String typeName;
}
