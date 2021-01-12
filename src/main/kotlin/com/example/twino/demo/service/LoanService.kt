package com.example.twino.demo.service

import com.example.twino.demo.api.dto.*
import org.springframework.data.domain.*
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
     * @param listData данные для формирования страницы
     * @return список всех кредитных заявок
     */
    fun list(listData: ListRequest) : Page<LoanDto>

    /**
     * Удаляет заявку
     * @param id айди заявки
     */
    fun delete(id: UUID)

    /**
     * Проставляет оценку заявке
     *
     * Если параметр data.mark отсутсвует, то вызывает автоматичесую оценку, иначе проставляет отправленную оценку
     *
     * @param data двнные заявки и новой отметки
     */
    fun mark(data: LoanMarkDto)
}

