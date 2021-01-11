package com.example.twino.demo.service

import com.example.twino.demo.api.dto.*
import com.example.twino.demo.domain.Loan
import com.example.twino.demo.domain.Loan.Companion
import com.example.twino.demo.repository.LoanRepository
import org.slf4j.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class LoanServiceImpl(
    private val repo: LoanRepository
): LoanService {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Transactional
    override fun create(loanDto: LoanDto): UUID {
        val loan = Loan.fromDto(loanDto)
        val result = repo.save(loan)
        log.debug("loan created: $loan")
        return result.id
    }

    @Transactional
    override fun list(): List<LoanDto>{
        val result = repo.findAll().map { it.toDto() }
        log.debug("loans found: $result")
        return result
    }

    @Transactional
    override fun delete(id: UUID) {
        repo.deleteById(id)
        log.debug("loan deleted, id = $id")
    }

    @Transactional
    override fun mark(data: LoanMarkDto) {
        validateMark(data.mark)
        val loan: Loan = repo.getOne(data.id)
        loan.mark = data.mark
        log.debug("loan marked $data")
    }

    internal fun validateMark(mark: String){
        val validMarks = setOf("Approved", "Manual", "Rejected")
        if (mark !in validMarks){
            throw IllegalArgumentException()
        }
    }


}