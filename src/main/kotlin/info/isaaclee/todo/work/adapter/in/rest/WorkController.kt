package info.isaaclee.todo.work.adapter.`in`.rest

import info.isaaclee.todo.work.adapter.`in`.rest.dto.req.PutWorkStateReqDto
import info.isaaclee.todo.work.adapter.`in`.rest.dto.res.GetWorkResDto
import info.isaaclee.todo.work.adapter.`in`.rest.dto.res.PutWorkStateResDto
import info.isaaclee.todo.work.application.port.`in`.ChangeWorkStateCommand
import info.isaaclee.todo.work.application.port.`in`.ChangeWorkStateUseCase
import info.isaaclee.todo.work.application.port.`in`.GetWorkQuery
import info.isaaclee.todo.work.application.port.`in`.GetWorkUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/work")
class WorkController(
	private val getWorkUseCase: GetWorkUseCase,
	private val changeWorkStateUseCase: ChangeWorkStateUseCase
) {
	@GetMapping("/{workId}")
	fun getWork(@PathVariable("workId") workId: Long): GetWorkResDto {
		val query = GetWorkQuery(workId)
		val work = this.getWorkUseCase.getWork(query)
		return GetWorkResDto(work)
	}

	@PutMapping("/{workId}/state")
	fun putWork(
		@PathVariable("workId") workId: Long,
		@RequestBody body: PutWorkStateReqDto
	): PutWorkStateResDto {
		val command = ChangeWorkStateCommand(workId, body.state)
		val state = this.changeWorkStateUseCase.changeState(command)
		return PutWorkStateResDto(state)
	}
}
