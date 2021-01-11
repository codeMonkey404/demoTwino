package com.example.twino.demo.service

import com.example.twino.demo.repository.LoanRepository
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.example.twino.demo.service"])
class TestConfig {

    @MockBean
    lateinit var repo: LoanRepository

}