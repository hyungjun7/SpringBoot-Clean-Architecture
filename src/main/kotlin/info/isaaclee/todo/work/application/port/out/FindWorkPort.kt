package info.isaaclee.todo.work.application.port.out

import info.isaaclee.todo.work.domain.Work

interface FindWorkPort {
	fun findWork(workId: Long): Work?
}
