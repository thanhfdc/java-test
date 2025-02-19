package com.metasol.repositories;

import com.metasol.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeRepository extends JpaRepository<TypeEntity, Long> {
}
