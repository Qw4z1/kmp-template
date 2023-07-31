import io.nyblom.readchecker.BuildKonfig

fun blogPostUrl(slug: String) = "${BuildKonfig.BLOG_BASE_URL}/${slug}"

expect fun openBlogPost(slug: String)

expect fun copyBlogPostUrl(slug: String)

expect class ContextWrapper private constructor()
