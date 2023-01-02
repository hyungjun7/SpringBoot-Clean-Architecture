package info.isaaclee.todo.work.application.port.`in`

import org.springframework.stereotype.Service

@Service
interface ChangeWorkStateUseCase {
	fun changeState(command: ChangeWorkStateCommand): Boolean
}
