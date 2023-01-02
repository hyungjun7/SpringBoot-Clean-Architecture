package info.isaaclee.todo.work.application.port.out

import info.isaaclee.todo.work.domain.Work

interface UpdateWorkStatePort {
	fun updateState(work: Work)
}
