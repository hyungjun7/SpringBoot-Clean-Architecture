package info.isaaclee.todo.work.adapter.out.mapper

import info.isaaclee.todo.work.adapter.out.persistence.WorkJpaEntity
import info.isaaclee.todo.work.domain.Work
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class WorkMapper {
	fun toDomainEntity(entity: WorkJpaEntity): Work {
		return Work(
			id = entity.id,
			contents = entity.contents,
			state = entity.state,
			createdAt = entity.createdAt as LocalDateTime
		)
	}

	fun toJpaEntity(work: Work): WorkJpaEntity {
		return WorkJpaEntity(
			id = work.id,
			contents = work.contents,
			state = work.state,
			createdAt = work.createdAt
		)
	}
}
