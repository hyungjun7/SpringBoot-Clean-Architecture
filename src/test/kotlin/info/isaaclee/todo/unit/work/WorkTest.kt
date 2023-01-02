package info.isaaclee.todo.unit.work

import info.isaaclee.todo.work.domain.Work
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import java.time.LocalDateTime

class WorkTest {
	@DisplayName("할 일 상태 변경 가능")
	@Test
	fun canChangeState() {
		val work = Work(
			id = 1L,
			contents = "asdasd",
			state = false,
			createdAt = LocalDateTime.now()
		)
		assertEquals(work.canChangeState(), true)
	}

	@DisplayName("할 일 상태 변경 불가능")
	@Test
	fun cannotChangeState() {
		val work = Work(
			id = 1L,
			contents = "asdasd",
			state = false,
			createdAt = LocalDateTime.now().minusDays(2)
		)
		assertEquals(work.canChangeState(), false)
	}
}
