package info.isaaclee.todo.work.adapter.out.persistence

import info.isaaclee.todo.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime

@Entity
@Table(name = "work")
class WorkJpaEntity(
	override val id: Long?,

	@Column(nullable = false)
	var contents: String,

	@Column(nullable = false)
	@ColumnDefault("false")
	var state: Boolean,

	override val createdAt: LocalDateTime?,
): BaseEntity()
