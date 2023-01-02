package info.isaaclee.todo.work.domain

import java.time.LocalDate
import java.time.LocalDateTime

class Work(
	val id: Long?,
	val contents: String,
	var state: Boolean,
	val createdAt: LocalDateTime
) {
	fun canChangeState(): Boolean {
		if (this.isAfterDay()) {
			return false
		}
		return true
	}

	private fun isAfterDay(): Boolean {
		return LocalDate.now().isAfter(this.createdAt.toLocalDate())
	}
}
