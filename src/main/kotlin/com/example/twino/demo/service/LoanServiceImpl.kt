package com.example.twino.demo.service

import com.example.twino.demo.api.dto.*
import com.example.twino.demo.domain.Loan
import com.example.twino.demo.repository.LoanRepository
import org.slf4j.*
import org.springframework.data.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class LoanServiceImpl(
    private val repo: LoanRepository,
    private val scoring: ScoringService
) : LoanService {

    companion object {
        private const val APPROVED_MARK = "Approved"
        private const val APPROVED_SCORE = 3500

        private const val MANUAL_MARK = "Manual"

        private const val REJECTED_MARK = "Rejected"
        private const val REJECTED_SCORE = 2500

        val marks = setOf(APPROVED_MARK, MANUAL_MARK, REJECTED_MARK)

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
    override fun list(listData: ListRequest): Page<LoanDto> {

        val sort = getSort(listData.sorting)
        val elementsOnPage = listData.paging?.pageSize ?: 10
        val pageNumber = listData.paging?.pageNumber ?: 0

        val pageable = PageRequest.of(elementsOnPage, pageNumber, sort)
        val result = repo.findAll(pageable).map { it.toDto() }
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

        if (data.mark.isNullOrEmpty()) {
            loan.mark = getMark(loan)
        } else {
            loan.mark = data.mark
        }
        log.debug("loan marked $data")
    }

    internal fun validateMark(mark: String?) {
        if (mark != null && mark !in marks) {
            throw IllegalArgumentException()
        }
    }

    internal fun getMark(loan: Loan): String {
        val score = scoring.getScore(loan)
        return when {
            score >= APPROVED_SCORE -> {
                APPROVED_MARK
            }
            score <= REJECTED_SCORE -> {
                REJECTED_MARK
            }
            else -> {
                MANUAL_MARK
            }
        }
    }

    internal fun getSort(sorting: Sorting?) = if (sorting != null) sortingToSort(sorting) else Sort.unsorted()

    internal fun sortingToSort(sorting: Sorting): Sort{
        return if (sorting.orderDirectionASC) {Sort.by(sorting.field).ascending()}
        else {Sort.by(sorting.field).descending()}
    }


}