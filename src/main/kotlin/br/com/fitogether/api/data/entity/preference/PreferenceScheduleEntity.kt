package br.com.fitogether.api.data.entity.preference

import java.time.LocalTime
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "preference_schedules")
data class PreferenceScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne()
    @JoinColumn(name = "preference_id", nullable = false)
    val preference: PreferenceEntity? = null,

    @Column(name = "day_week", nullable = false)
    val dayWeek: Int = 0,

    @Column(name = "start_time", nullable = false)
    val startTime: LocalTime? = null,

    @Column(name = "end_time", nullable = false)
    val endTime: LocalTime? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
)
