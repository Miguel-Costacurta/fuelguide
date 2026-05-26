package com.fuelguide.api.posto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostoRepository extends JpaRepository<PostoEntity, Long> {
    List<PostoEntity> findByCidade(String cidade);
}
