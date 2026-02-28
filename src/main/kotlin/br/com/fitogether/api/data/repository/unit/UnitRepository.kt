package br.com.fitogether.api.data.repository.unit

import br.com.fitogether.api.data.entity.unit.UnitEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UnitRepository : JpaRepository<UnitEntity, Long> {
    @Query(
        value = """
        SELECT u.* FROM units u 
        INNER JOIN addresses a ON u.address_id = a.id
        WHERE u.deleted_at IS NULL
        AND ST_Distance_Sphere(
            a.coordinates,
            ST_GeomFromText(CONCAT('POINT(', :lng, ' ', :lat, ')'))
        ) <= :radius
        """,
        nativeQuery = true
    )
    fun findUnitsWithinLatLng(
        @Param("lat") lat: Double,
        @Param("lng") lng: Double,
        @Param("radius") radius: Double
    ): List<UnitEntity>

    @Query(
        value = """
        SELECT DISTINCT u.* FROM units u 
        INNER JOIN addresses a ON u.address_id = a.id
        INNER JOIN unit_exercises ue ON ue.unit_id = u.id
        WHERE u.deleted_at IS NULL
        AND ST_Distance_Sphere(
            a.coordinates,
            ST_GeomFromText(CONCAT('POINT(', :lng, ' ', :lat, ')'))
        ) <= :radius
        AND ue.exercise_id IN :exerciseIds
        """,
        nativeQuery = true
    )
    fun findUnitsWithinLatLngAndExercise(
        @Param("lat") lat: Double,
        @Param("lng") lng: Double,
        @Param("radius") radius: Double,
        @Param("exerciseIds") exerciseIds: List<Long>
    ): List<UnitEntity>
}
