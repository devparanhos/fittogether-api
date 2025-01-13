package br.com.fitogether.api.data.entity.preference

import br.com.fitogether.api.data.entity.BaseEntity
import java.time.LocalTime
import java.time.LocalDateTime
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity(name = "preference_schedules")
@SQLDelete(sql = "UPDATE preference_schedules SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
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
) : BaseEntity()
