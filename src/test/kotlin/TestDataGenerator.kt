import org.apache.commons.lang3.RandomStringUtils
import java.math.BigDecimal
import kotlin.random.Random.Default.nextInt

class TestDataGenerator {
    companion object{
        fun str(length: Int = 10, useLetters: Boolean = true, useNumbers: Boolean = false): String {
            return RandomStringUtils.random(length, useLetters, useNumbers)
        }

        fun randomDec(): BigDecimal {
            return BigDecimal(nextInt())
        }
    }
}