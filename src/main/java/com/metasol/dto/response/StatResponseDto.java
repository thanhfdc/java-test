package com.metasol.dto.response;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatResponseDto {
    private Long id;
    private String name;
    private int total_quantity_sold;

}
