import androidx.compose.ui.window.ComposeUIViewController
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIPasteboard

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController { App() }

actual class ContextWrapper private actual constructor()

actual fun openBlogPost(slug: String) {
    UIApplication.sharedApplication.openURL(NSURL(string = blogPostUrl(slug)))
}

actual fun copyBlogPostUrl(slug: String) =
    UIPasteboard.generalPasteboard.setString(blogPostUrl(slug))