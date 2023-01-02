package info.isaaclee.todo.work.application.service

import info.isaaclee.todo.common.exceptions.NotFoundException
import info.isaaclee.todo.work.application.port.`in`.GetWorkQuery
import info.isaaclee.todo.work.application.port.`in`.GetWorkUseCase
import info.isaaclee.todo.work.application.port.out.FindWorkPort
import info.isaaclee.todo.work.domain.Work

class GetWorkService(
	private val findWorkPort: FindWorkPort
): GetWorkUseCase {
	override fun getWork(query: GetWorkQuery): Work {
		return this.findWorkPort.findWork(query.workId) ?: throw NotFoundException()
	}
}
