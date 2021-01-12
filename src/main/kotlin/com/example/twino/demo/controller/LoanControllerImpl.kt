package com.example.twino.demo.controller

import com.example.twino.demo.api.LoanController
import com.example.twino.demo.api.dto.*
import com.example.twino.demo.service.LoanService
import org.slf4j.*
import org.springframework.data.domain.*
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class LoanControllerImpl(
    private val service: LoanService
): LoanController {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun create(loanDto: LoanDto): UUID {
        log.debug("/api/v1/loan/create call with: $loanDto")
        return service.create(loanDto)
    }

    override fun list(listData: ListRequest) : Page<LoanDto> {
        log.debug("/api/v1/loan/list call")
        return service.list(listData)
    }

    override fun delete(data: LoanDeleteDto){
        log.debug("/api/v1/loan/delete call with id = ${data.id}")
        service.delete(data.id)
    }

    override fun mark(data: LoanMarkDto) {
        log.debug("/api/v1/loan/mark call with $data")
        service.mark(data)
    }
}