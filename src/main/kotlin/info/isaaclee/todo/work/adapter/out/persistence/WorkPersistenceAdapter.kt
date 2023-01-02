package info.isaaclee.todo.work.adapter.out.persistence

import info.isaaclee.todo.work.adapter.out.mapper.WorkMapper
import info.isaaclee.todo.work.application.port.out.FindWorkPort
import info.isaaclee.todo.work.application.port.out.UpdateWorkStatePort
import info.isaaclee.todo.work.domain.Work
import org.springframework.stereotype.Component

@Component
class WorkPersistenceAdapter(
	private val workRepository: WorkRepository,
	private val workMapper: WorkMapper
): FindWorkPort, UpdateWorkStatePort {
	override fun findWork(workId: Long): Work? {
		val work = this.workRepository.findById(workId)
		if (work.isEmpty) {
			return null
		}
		return this.workMapper.toDomainEntity(work.get())
	}

	override fun updateState(work: Work) {
		val entity = this.workMapper.toJpaEntity(work)
		this.workRepository.save(entity)
	}
}
