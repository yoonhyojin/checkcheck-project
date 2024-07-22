package com.example.checkcheck.memo.controller

import com.example.checkcheck.common.dtos.BaseResponse
import com.example.checkcheck.memo.dto.MemoRequestDto
import com.example.checkcheck.memo.dto.MemoResponseDto
import com.example.checkcheck.memo.service.MemoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "BearerAuth")
@Tag(name = "메모 Api 컨트롤러", description =  "게시글 생성,조회,수정,삭제 Api 명세서입니다.")

@RestController
@RequestMapping("/api/memos")
class MemoController {

    @Autowired
    private lateinit var memoService: MemoService

    /**
     * 모든 메모를 조회하는 Api
     */
    @GetMapping
    private fun getMemos() : ResponseEntity<BaseResponse<List<MemoResponseDto>>> {
        val result = memoService.getMemos()
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
    * 메모를 생성하는 Api
    */
    @PostMapping
    private fun postMemo(@Valid @RequestBody memoRequestDto: MemoRequestDto) :
            ResponseEntity<BaseResponse<MemoResponseDto>> {
        val result = memoService.postMemos(memoRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse(data = result))
    }

    /**
     * 메모 수정 Api
     */
    @PutMapping("/{id}")
    private fun putMemo(@Valid @RequestBody memoRequestDto: MemoRequestDto, @PathVariable id : Long) :
            ResponseEntity<BaseResponse<MemoResponseDto>> {
        val result = memoService.putMemos(memoRequestDto, id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * 메모 삭제 Api
     */
    @DeleteMapping("/{id}")
    private fun deleteMemo(@PathVariable id : Long) : ResponseEntity<BaseResponse<Any>> {
        memoService.deleteMemo(id)
        return ResponseEntity.ok().body(BaseResponse(data = null))
    }


    /**
     * 특정 사용자의 모든 강의 메모 조회 및 검색 API
     */
    @GetMapping("/search")
    fun getMemosByMemberWithSearch(@RequestParam memberId: Long, @RequestParam query: String):
            ResponseEntity<BaseResponse<List<MemoResponseDto>>> {
        val result = memoService.getMemosByMemberWithSearch(memberId, query)
        return ResponseEntity.ok(BaseResponse(data = result))
    }


    /**
     * 특정 사용자의 특정 강의에 해당하는 메모 가져오는 Api
     */
    @GetMapping("/member/{memberId}/lecture/{lectureId}")
    private fun getMemosByMemberAndLecture(@PathVariable memberId: Long, @PathVariable lectureId: Long):
            ResponseEntity<BaseResponse<List<MemoResponseDto>>> {
        val result = memoService.getMemosByMemberAndLecture(memberId, lectureId)
        return ResponseEntity.ok(BaseResponse(data = result))
    }
}