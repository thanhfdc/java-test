package com.metasol.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "types")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

}
