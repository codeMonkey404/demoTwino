package com.example.twino.demo.service

import com.example.twino.demo.domain.Loan
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * Выполняет расчет значений для скоринга кредитной заявки
 */
interface ScoringService {

    /**
     * Выполняет расчет значений для скоринга кредитной заявки
     * @param loan заявка
     * @return значение для скоринга
     */
    fun getScore(loan: Loan): Int
}

@Service
class ScoringServiceImpl : ScoringService{

    companion object{
        val letters = "abcdefghijklmnopqrstuvwxyz".toCharArray()

        val latterValues = mutableMapOf<Char, Int>()
    }

    init {
        var value = 1
        for (i in letters.indices){
            latterValues[letters[i]] = value++
        }
    }

    override fun getScore(loan: Loan): Int {
        val nameScore = getFirstNameScore(loan.firstName)
        val salaryScore = loan.salary.multiply(BigDecimal.valueOf(1.5)).toInt()
        val monthlyLiabilityScore = loan.monthlyLiability.multiply(BigDecimal.valueOf(3)).toInt()

        return (nameScore
            + salaryScore
            - monthlyLiabilityScore
            + loan.birthDate.year
            - loan.birthDate.month.value
            - loan.birthDate.dayOfYear)
    }


    /**
     * Возвращает значение скоринга имени
     * @param name имя
     * @return значение для скоринга
     */
    internal fun getFirstNameScore(name :String): Int {
        var result = 0
        name.toLowerCase().forEach {
            result += latterValues[it]!!
        }
        return result
    }

}