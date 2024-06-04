import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

interface DataSource

class DatabaseDataSource: DataSource

class NetworkDataSource: DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource
    companion object {
        inline fun <reified T : DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw IllegalArgumentException("Unsupported type")
            }
    }
}

class NetworkFactory: DataSourceFactory() {
    override fun makeDataSource(): DataSource {
        return NetworkDataSource()
    }
}

class DatabaseFactory: DataSourceFactory() {
    override fun makeDataSource(): DataSource {
        return DatabaseDataSource()
    }
}

class AbstractFactoryTest {
    @Test
    fun abstractFactoryTest() {
        val dataSourceFactory = DataSourceFactory.createFactory<DatabaseDataSource>()
        val dataSource: DataSource = dataSourceFactory.makeDataSource()
        println("Created data source $dataSource")
        assertTrue(dataSource is DatabaseDataSource)
    }
}