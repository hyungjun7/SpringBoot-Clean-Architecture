package info.isaaclee.todo.work.application.port.`in`

import info.isaaclee.todo.common.ValidateSelf
import org.springframework.lang.NonNull

class ChangeWorkStateCommand: ValidateSelf<ChangeWorkStateCommand> {
	@NonNull
	val workId: Long
	@NonNull
	val state: Boolean

	constructor(workId: Long, state: Boolean) {
		this.workId = workId
		this.state = state

		requireNotNull(this.workId)
		requireNotNull(this.state)
		this.validateSelf()
	}
}
