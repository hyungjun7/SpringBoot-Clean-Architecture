package info.isaaclee.todo.work.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface WorkRepository: JpaRepository<WorkJpaEntity, Long> {
}
