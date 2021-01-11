package com.example.twino.demo.service

import com.example.twino.demo.api.dto.*
import java.util.UUID

/**
 * Выполняет операции связанные с кредитными заявками
 *
 * На данный момоент выполняет только операцию сохранения кредитной заявки
 */
interface LoanService {

    /**
     * Сохраняет кредитную заявку
     * @param loanDto данные кредитной заявки
     * @return айди созданной заявки
     */
    fun create(loanDto: LoanDto): UUID

    /**
     * Возвращает список всех кредитных заявок
     * @return список всех кредитных заявок
     */
    fun list() : List<LoanDto>

    /**
     * Удаляет заявку
     * @param id айди заявки
     */
    fun delete(id: UUID)

    /**
     * Проставляет оценку заявке
     * @param data двнные заявки и новой отметки
     */
    fun mark(data: LoanMarkDto)
}

