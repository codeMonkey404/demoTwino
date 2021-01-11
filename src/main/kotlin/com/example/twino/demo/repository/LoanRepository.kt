package com.example.twino.demo.repository

import com.example.twino.demo.domain.Loan
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface LoanRepository : JpaRepository<Loan, UUID> {
}