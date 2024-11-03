package app.ice.harmoniqa.ui.screen.settings

import android.annotation.SuppressLint
import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import app.ice.harmoniqa.R
import app.ice.harmoniqa.ui.theme.md_theme_dark_background
import app.ice.harmoniqa.ui.theme.md_theme_dark_primary
import app.ice.harmoniqa.ui.theme.md_theme_dark_surface
import app.ice.harmoniqa.utils.resString
import app.ice.harmoniqa.viewModel.SettingsViewModel
import app.ice.harmoniqa.viewModel.SharedViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(UnstableApi::class)
@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    sharedViewModel: SharedViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController? = null,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = context.getString(R.string.settings))
            },
                navigationIcon = {
                    IconButton(onClick = {
                        navController?.popBackStack()
                    }) {
                        Icon(painterResource(R.drawable.baseline_arrow_back_ios_new_24), contentDescription = "back_icon")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column {
                CategoryTitle(title = resString(R.string.user_interface))
                ToggleSettingItem(title = resString(resId = R.string.translucent_bottom_navigation_bar),
                    value = viewModel.translucentBottomBar.collectAsState().value == "TRUE",
                    onChange = { value ->
                        viewModel.setTranslucentBottomBar(value)
                    })
                CategoryTitle(title = resString(resId = (R.string.content)))
                ClickableItems(
                    title = resString(resId = R.string.youtube_account),
                    description = resString(resId = R.string.manage_your_youtube_accounts),
                    onClick = {})
                ClickableItems(title = resString(resId = R.string.language), description = viewModel.language.collectAsState().value, onClick = {})
                ClickableItems(title = resString(resId = R.string.content_country), description = viewModel.location.collectAsState().value, onClick = {})
                ClickableItems(title = resString(resId = R.string.audio_quality), description = viewModel.quality.collectAsState().value, onClick = {})
                ToggleSettingItem(
                    title = resString(resId = R.string.play_video_for_video_track_instead_of_audio_only),
                    value = viewModel.playVideoInsteadOfAudio.collectAsState().value == "TRUE",
                    onChange = { value -> viewModel.setPlayVideoInsteadOfAudio(value) })
            }
        }
    }
}

@Composable
private fun CategoryTitle(title: String) {
    Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp, bottom = 10.dp, top = 10.dp))
}

@Composable
fun ClickableItems(title: String, description: String? = null, onClick: (() -> Unit)) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(start = 25.dp, end = 20.dp, top = 7.dp, bottom = 7.dp)
    ) {
        Text(title)
        if (description != null)
            Text(text = description, fontSize = 14.sp, color = MaterialTheme.colorScheme.outline)
    }
}

@Composable
fun ToggleSettingItem(title: String, description: String? = null, value: Boolean, onChange: ((Boolean) -> Unit)) {
    Box(modifier = Modifier
        .clickable {
            onChange(!value)
        }
        .padding(start = 25.dp, end = 20.dp, top = 7.dp, bottom = 7.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(title, maxLines = 2, modifier = Modifier.width(275.dp))
                if (description != null)
                    Text(text = description)
            }
            Switch(
                checked = value, onCheckedChange = onChange, colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.background,
                    checkedTrackColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    MaterialTheme(colorScheme = darkColorScheme(surface = md_theme_dark_surface, background = md_theme_dark_background)) {
        SettingsScreen()
    }
}