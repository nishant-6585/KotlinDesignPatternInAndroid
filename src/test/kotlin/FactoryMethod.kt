import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

sealed class Country {
    data object Canada: Country()
}

data object Spain : Country()
class Greece(val someProperty: String) : Country()
data class USA(val someProperty: String) : Country()
//class Poland : Country()

class Currency(val code: String)

object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency {
        return when (country) {
            is Spain -> Currency("EUR")
            is Greece -> Currency("EUR")
            is USA -> Currency("USD")
            is Country.Canada -> Currency("CAD")
        }
    }
}

class FactoryMethodTest {
    @Test
    fun currencyTest() {
        val geekCurrency = CurrencyFactory.currencyForCountry(Greece("")).code
        println("Greek currency: $geekCurrency")

        val usaCurrency = CurrencyFactory.currencyForCountry(USA("")).code
        println("USA currency: $usaCurrency")

        Assertions.assertEquals(geekCurrency, "EUR")
        Assertions.assertEquals(usaCurrency, "USD")

    }
}