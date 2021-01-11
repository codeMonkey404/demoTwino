package com.example.twino.demo

import org.slf4j.*
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.*
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

/**
 * Перехватывает и подменяет ошибки приложения
 */
@RestControllerAdvice
class GlobalExceptionHandler{

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccess(ex: Exception): ResponseEntity<Message> {
        log.error(ex.message, ex)
        val message = Message("Проверьте входные данные")
        return ResponseEntity(message, HttpHeaders(), BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: Exception): ResponseEntity<Message> {
        log.error(ex.message, ex)
        val message = Message("Проверьте входные данные")
        return ResponseEntity(message, HttpHeaders(), BAD_REQUEST)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(ex: Exception): ResponseEntity<Message> {
        log.error(ex.message, ex)
        val message = Message("Проверьте входные данные")
        return ResponseEntity(message, HttpHeaders(), BAD_REQUEST)
    }
}

/**
 * Сообщение об ошибке которое используется в ответе [ResponseEntity]
 */
data class Message(
    /**
     * Текст сообщения
     */
    val message: String
)