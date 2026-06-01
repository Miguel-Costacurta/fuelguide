package com.fuelguide.api.posto;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPostoRepository extends JpaRepository<PostoEntity, Long> {
    List<PostoEntity> findByCidade(String cidade);
    Optional<PostoEntity> findByCnpj(String cnpj);
    List<PostoEntity> findByCidadeAndPrecos_Tipo(String cidade, ETipoCombustivel tipo);

    @Query(value = """
        SELECT * FROM tlb_postos
        WHERE ST_DWithin(
            ST_MakePoint(lon, lat)::geography,
            ST_GeomFromText(:linestring, 4326)::geography,
            :raioMetros
        )
    """, nativeQuery = true)
    List<PostoEntity> buscarPostosPorRota(
            @Param("linestring") String linestring,
            @Param("raioMetros") double raioMetros
    );

    @Query(value = "SELECT DISTINCT cidade, estado FROM tlb_postos", nativeQuery = true)
    List<Object[]> buscarCidadesUnicas();

    @Modifying
    @Transactional
    @Query(value = "UPDATE tlb_postos SET lat = :lat, lon = :lon WHERE cidade = :cidade AND estado = :estado", nativeQuery = true)
    void atualizarCoordenadas(
            @Param("cidade") String cidade,
            @Param("estado") String estado,
            @Param("lat") double lat,
            @Param("lon") double lon
    );
    List<PostoEntity> findByLatIsNull();
}
