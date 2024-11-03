package app.ice.harmoniqa.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun resString(resId: Int): String {
    return LocalContext.current.getString(resId)
}