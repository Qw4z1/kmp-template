import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import io.nyblom.readchecker.BuildKonfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

data class ReadsUiState(
    val reads: List<Read> = emptyList(),
)

class ReadsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ReadsUiState())
    val uiState = _uiState.asStateFlow()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    init {
        updateReads()
    }

    override fun onCleared() {
        httpClient.close()
    }

    fun updateReads() {
        viewModelScope.launch {
            val reads = getReads()
            _uiState.update {
                it.copy(reads = reads)
            }
        }
    }

    private suspend fun getReads(): List<Read> {
        val res = httpClient
            .request {
                header("Authorization", "Bearer ${BuildKonfig.API_TOKEN}")
                url(BuildKonfig.API_URL)
            }
        return res.body()
    }
}