package com.fuelguide.api.posto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPostoRepository extends JpaRepository<PostoEntity, Long> {
    List<PostoEntity> findByCidade(String cidade);
    Optional<PostoEntity> findByCnpj(String cnpj);
    List<PostoEntity> findByCidadeAndPrecos_Tipo(String cidade, ETipoCombustivel tipo);
}
