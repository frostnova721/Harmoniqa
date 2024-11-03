package app.ice.harmoniqa.ui.screen.login

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import app.ice.harmoniqa.viewModel.LogInViewModel
import app.ice.harmoniqa.viewModel.SettingsViewModel
import app.ice.harmoniqa.viewModel.SharedViewModel

@OptIn(UnstableApi::class)
@Composable
fun LogInScreen(
    logInViewModel: LogInViewModel = viewModel(),
    settingsViewModel: SettingsViewModel = viewModel(),
    sharedViewModel: SharedViewModel = viewModel(),
) {

}