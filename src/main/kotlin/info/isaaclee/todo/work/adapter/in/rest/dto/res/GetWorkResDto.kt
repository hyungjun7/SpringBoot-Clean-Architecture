package info.isaaclee.todo.work.adapter.`in`.rest.dto.res

import info.isaaclee.todo.work.domain.Work
import java.time.LocalDateTime

data class GetWorkResDto(
	val id: Long,
	val contents: String,
	val state: Boolean,
	val createdAt: LocalDateTime
) {

	constructor(work: Work): this(
		id = work.id as Long,
		contents = work.contents,
		state = work.state,
		createdAt = work.createdAt
	)
}
