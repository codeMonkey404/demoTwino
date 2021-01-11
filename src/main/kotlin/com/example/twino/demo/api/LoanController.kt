package com.example.twino.demo.api

import com.example.twino.demo.api.dto.*
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RequestMapping("/api/v1/loan")
interface LoanController {

    @PostMapping("create")
    fun create(@RequestBody loanDto: LoanDto): UUID

    @PostMapping("list")
    fun list() : List<LoanDto>

    @PostMapping("delete")
    fun delete(@RequestBody data: LoanDeleteDto)

    @PostMapping("mark")
    fun mark(@RequestBody data: LoanMarkDto)
}