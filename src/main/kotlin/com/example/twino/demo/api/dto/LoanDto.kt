package com.example.twino.demo.api.dto

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID


data class LoanDto (
    val id: UUID? = null,
    val personalId: UUID,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    val employer: String,
    val salary: BigDecimal,
    val monthlyLiability: BigDecimal,
    val requestedAmount: BigDecimal,
    val requestedTermMonths: Int,
    val mark: String? = null
    )