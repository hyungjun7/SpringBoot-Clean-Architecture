package info.isaaclee.todo.work.application.service

import info.isaaclee.todo.common.exceptions.ConflictException
import info.isaaclee.todo.common.exceptions.NotFoundException
import info.isaaclee.todo.work.application.port.`in`.ChangeWorkStateCommand
import info.isaaclee.todo.work.application.port.`in`.ChangeWorkStateUseCase
import info.isaaclee.todo.work.application.port.out.FindWorkPort
import info.isaaclee.todo.work.application.port.out.UpdateWorkStatePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ChangeWorkStateService(
	private val findWorkPort: FindWorkPort,
	private val updateWorkStatePort: UpdateWorkStatePort
): ChangeWorkStateUseCase {

	override fun changeState(command: ChangeWorkStateCommand): Boolean {
		val work = this.findWorkPort.findWork(command.workId) ?: throw NotFoundException()
		if (!work.canChangeState()) {
			throw ConflictException("expired")
		}
		work.state = command.state
		this.updateWorkStatePort.updateState(work)
		return command.state
	}
}
