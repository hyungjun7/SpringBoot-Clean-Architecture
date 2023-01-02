package info.isaaclee.todo.unit.work.service

import info.isaaclee.todo.common.exceptions.ConflictException
import info.isaaclee.todo.common.exceptions.NotFoundException
import info.isaaclee.todo.work.application.port.`in`.ChangeWorkStateCommand
import info.isaaclee.todo.work.application.port.out.FindWorkPort
import info.isaaclee.todo.work.application.port.out.UpdateWorkStatePort
import info.isaaclee.todo.work.application.service.ChangeWorkStateService
import info.isaaclee.todo.work.domain.Work
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.BDDMockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(MockitoExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChangeWorkStateServiceTest(
	@Mock val findWorkPort: FindWorkPort,
	@Mock val updateWorkStatePort: UpdateWorkStatePort,
) {

	private lateinit var changeWorkStateService: ChangeWorkStateService

	@BeforeAll
	internal fun init() {
		this.changeWorkStateService = ChangeWorkStateService(
			this.findWorkPort,
			this.updateWorkStatePort
		)
	}

	@DisplayName("상태 변경 성공")
	@Test
	fun changeStateSuccess() {
		val work = Work(id = 1L, contents = "asd", state = false, createdAt = LocalDateTime.now())
		val command = ChangeWorkStateCommand(
			workId = work.id as Long,
			state = true
		)
		given(findWorkPort.findWork(command.workId)).willReturn(work)
		willDoNothing().given(updateWorkStatePort).updateState(work)

		val state = this.changeWorkStateService.changeState(command)

		assertEquals(state, command.state)
	}

	@DisplayName("날짜 지남으로 인해 상태 변경 실패")
	@Test
	fun changeStateFailed() {
		val work = Work(id = 1L, contents = "asd", state = false, createdAt = LocalDateTime.now().minusDays(2))
		val command = ChangeWorkStateCommand(
			workId = work.id as Long,
			state = true
		)
		given(findWorkPort.findWork(command.workId)).willReturn(work)

		try {
			this.changeWorkStateService.changeState(command)
		} catch (e: ConflictException) {
			assertEquals(e.message, "expired")
		}
	}

	@DisplayName("workId 해당하는 항목을 못 찾음")
	@Test
	fun workNotFound() {
		val command = ChangeWorkStateCommand(
			workId = 2L,
			state = true
		)
		given(findWorkPort.findWork(command.workId)).willReturn(null)

		try {
			this.changeWorkStateService.changeState(command)
		} catch (e: NotFoundException) {
			assertEquals(e.message, "not_found")
		}
	}
}
