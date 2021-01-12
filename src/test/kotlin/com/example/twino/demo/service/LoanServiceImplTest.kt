package com.example.twino.demo.service

import TestDataGenerator
import com.example.twino.demo.repository.LoanRepository
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.ContextConfiguration
import java.util.stream.Stream

@ContextConfiguration(classes = [TestConfig::class])
@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension::class)
internal class LoanServiceImplTest {

    companion object {
        private val validMarks = setOf("Approved", "Manual", "Rejected")

        @JvmStatic
        fun getValidMarks(): Stream<Arguments> =
            validMarks.map { Arguments.of(it) }.stream()
    }

    @Spy
    @InjectMocks
    private lateinit var subj: LoanServiceImpl

    @Mock
    private lateinit var repo: LoanRepository

    @Mock
    private lateinit var scoring: ScoringService

    private val generator = TestDataGenerator

    @ParameterizedTest
    @MethodSource("getValidMarks")
    fun validateMarkPositivePass(mark: String) {
        assertDoesNotThrow() {
            subj.validateMark(mark)
        }
    }

    @Test
    fun validateMarkFail() {
        val mark = generator.str()
        assertThrows(IllegalArgumentException::class.java) {
            subj.validateMark(mark)
        }
    }
}