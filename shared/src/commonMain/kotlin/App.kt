import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App() {
    ReadCheckerTheme {
        val readsViewModel = getViewModel(Unit, viewModelFactory { ReadsViewModel() })
        Scaffold(
            topBar = { TopAppBar(title = { Text("Items") }) }
        ) {
                ReadsList(readsViewModel)

        }
    }
}
