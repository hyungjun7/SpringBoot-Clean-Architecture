package info.isaaclee.todo.work.application.port.`in`

import info.isaaclee.todo.common.ValidateSelf
import org.springframework.lang.NonNull

class GetWorkQuery: ValidateSelf<GetWorkQuery> {
	@NonNull
	val workId: Long

	constructor(workId: Long) {
		this.workId = workId

		requireNotNull(this.workId)
		this.validateSelf()
	}
}
