import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun MainView() {
    contextWrapper = ContextWrapper(LocalContext.current)
    App()
}

private lateinit var contextWrapper: ContextWrapper

actual class ContextWrapper private actual constructor() {
    lateinit var current: Context
        private set

    constructor(context: Context) : this() {
        this.current = context
    }
}

actual fun openBlogPost(slug: String) {
    val uri = Uri.parse(blogPostUrl(slug))
    val intent = Intent(Intent.ACTION_VIEW, uri)
    contextWrapper.current.startActivity(intent)
}

actual fun copyBlogPostUrl(slug: String) {
    val url = blogPostUrl(slug)
    val clipboard =
        contextWrapper.current.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(slug, url)
    clipboard.setPrimaryClip(clipData)
}