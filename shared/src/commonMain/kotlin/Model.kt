import kotlinx.serialization.Serializable

@Serializable
data class Read(
    val slug: String,
    val name: String,
    val reads: Int
)
