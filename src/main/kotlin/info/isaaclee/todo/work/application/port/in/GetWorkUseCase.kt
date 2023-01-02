package info.isaaclee.todo.work.application.port.`in`

import info.isaaclee.todo.work.domain.Work
import org.springframework.stereotype.Service

@Service
interface GetWorkUseCase {
	fun getWork(query: GetWorkQuery): Work
}
