package com.example.twino.demo.domain

import com.example.twino.demo.api.dto.LoanDto
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID
import javax.persistence.*

/**
 * Сущность кредитной заявки
 */
@Entity
open class Loan(
    /**
     * первичный ключ
     */
    @Id
    open val id: UUID = UUID.randomUUID(),

    /**
     * Идентификатор земщика
     */
    open val personalId: UUID,

    /**
     * Имя заёмщика
     */
    open val firstName: String,

    /**
     * Фамилия заёмщика
     */
    open val lastName: String,

    /**
     * Дата рождения заёмщика
     */
    open val birthDate: LocalDate,

    /**
     * Место работы заёмщика
     */
    open val employer: String,

    /**
     * ЗП заёмщика
     */
    open val salary: BigDecimal,

    /**
     * Ежемесячный платеж
     */
    open val monthlyLiability: BigDecimal,

    /**
     * Сумма кредита
     */
    open val requestedAmount: BigDecimal,

    /**
     * Колличество месяцев
     */
    open val requestedTermMonths: Int,

    /**
     * Оценка заявки
     */
    open var mark: String? = null
) {

    companion object {
        fun fromDto(dto: LoanDto) =
            Loan(
                personalId = dto.personalId,
                firstName = dto.firstName,
                lastName = dto.lastName,
                birthDate = dto.birthDate,
                employer = dto.employer,
                salary = dto.salary,
                monthlyLiability = dto.monthlyLiability,
                requestedAmount = dto.requestedAmount,
                requestedTermMonths = dto.requestedTermMonths
            )
    }

    fun toDto() =
        LoanDto(
            id,
            personalId,
            firstName,
            lastName,
            birthDate,
            employer,
            salary,
            monthlyLiability,
            requestedAmount,
            requestedTermMonths,
            mark
        )
}