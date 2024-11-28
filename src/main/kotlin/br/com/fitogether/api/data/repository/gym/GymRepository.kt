package br.com.fitogether.api.data.repository.gym

import br.com.fitogether.api.data.entity.gym.GymEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GymRepository: JpaRepository<GymEntity, Long> {
    @Query(
        """
        SELECT g FROM gyms g 
        JOIN g.address a
        WHERE ST_Distance_Sphere(
            a.coordinates,
            ST_GeomFromText(CONCAT('POINT(', :lng, ' ', :lat, ')'))
        ) <= :radius
        """
    )
    fun findGymsWithinLatLng(
        @Param("lat") lat: Double,
        @Param("lng") lng: Double,
        @Param("radius") radius: Double
    ): List<GymEntity>
}