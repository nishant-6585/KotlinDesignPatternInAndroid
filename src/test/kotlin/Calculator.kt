import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Calculator {
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
}

class CalculatorTest {
    @Test
    fun testSum() {
        val calculator = Calculator()
        val result = calculator.sum(1, 2)
        Assertions.assertEquals(3, result)
    }
}