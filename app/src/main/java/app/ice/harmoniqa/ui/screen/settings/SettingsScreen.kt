package app.ice.harmoniqa.ui.screen.settings

import android.annotation.SuppressLint
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import app.ice.harmoniqa.R
import app.ice.harmoniqa.common.QUALITY
import app.ice.harmoniqa.extension.navigateSafe
import app.ice.harmoniqa.ui.theme.md_theme_dark_background
import app.ice.harmoniqa.ui.theme.md_theme_dark_primary
import app.ice.harmoniqa.ui.theme.md_theme_dark_surface
import app.ice.harmoniqa.utils.resString
import app.ice.harmoniqa.viewModel.SettingsViewModel
import app.ice.harmoniqa.viewModel.SharedViewModel
import coil.compose.AsyncImage


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(UnstableApi::class)
@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    sharedViewModel: SharedViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController? = null,
) {
    var ytaccountDialogBoxState by remember {
        mutableStateOf(false)
    };
    var audioQualityDialogBoxState by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    if (ytaccountDialogBoxState) {
        DialogBox(onDismiss = {
            ytaccountDialogBoxState = false
        }) {
            Text("Accounts")
            if (viewModel.loggedIn.collectAsState().value == "TRUE") {
                val accounts = viewModel.googleAccounts.collectAsState().value
                accounts?.forEach {
                    AccountCard(
                        username = it.name,
                        emailId = it.email,
                        img = it.thumbnailUrl,
                        onLogout = {
                            viewModel.logOutAllYouTube()
                        }
                    )
                }
            } else {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                ) {
                    Text("Not logged in", fontSize = MaterialTheme.typography.labelMedium.fontSize)
                }
            }
            AddAccountCard(onClick = {
                navController?.navigateSafe(R.id.action_global_logInFragment)
            })
        }

    }

    if (audioQualityDialogBoxState) {
        DialogBox(onDismiss = { audioQualityDialogBoxState = false }) {
            Text(text = resString(resId = R.string.audio_quality), modifier = Modifier.padding(bottom = 10.dp))
            QUALITY.items.forEachIndexed { index, it ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { viewModel.changeQuality(index) }) {
                    RadioButton(selected = it.toString() == viewModel.quality.collectAsState().value, onClick = { viewModel.changeQuality(index) })
                    Text(text = it.toString(), fontSize = MaterialTheme.typography.labelMedium.fontSize)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = resString(R.string.settings))
            },
                navigationIcon = {
                    IconButton(onClick = {
                        navController?.navigateUp()
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
                    onClick = {
                        ytaccountDialogBoxState = true
                    })
                ClickableItems(title = resString(resId = R.string.language), description = viewModel.language.collectAsState().value, onClick = {})
                ClickableItems(
                    title = resString(resId = R.string.content_country),
                    description = viewModel.location.collectAsState().value,
                    onClick = {})
                ClickableItems(
                    title = resString(resId = R.string.audio_quality),
                    description = viewModel.quality.collectAsState().value,
                    onClick = {
                        audioQualityDialogBoxState = true
                    })
                ToggleSettingItem(
                    title = resString(resId = R.string.play_video_for_video_track_instead_of_audio_only),
                    value = viewModel.playVideoInsteadOfAudio.collectAsState().value == "TRUE",
                    onChange = { value -> viewModel.setPlayVideoInsteadOfAudio(value) })
            }
        }
    }
}

@Composable
fun DialogBox(onDismiss: (() -> Unit), content: @Composable () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun AddAccountCard(onClick: (() -> Unit)) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .clickable {
            onClick()
        }
        .padding(top = 8.dp, bottom = 8.dp)
        .height(30.dp)
        .fillMaxWidth()) {
        Icon(painterResource(id = R.drawable.baseline_add_24), contentDescription = "add account")
        Text("Add account", fontSize = MaterialTheme.typography.labelMedium.fontSize)
    }
}

@Composable
private fun AccountCard(username: String, emailId: String, img: String, onLogout: (() -> Unit)) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .height(70.dp), contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = img, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(100))
            )
            Column(
                Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .width(130.dp)
            ) {
                Text(text = username, fontSize = MaterialTheme.typography.labelMedium.fontSize, overflow = TextOverflow.Ellipsis, maxLines = 2)
                Text(text = emailId, fontSize = MaterialTheme.typography.bodySmall.fontSize, overflow = TextOverflow.Ellipsis, maxLines = 1)
            }
            OutlinedButton(onClick = onLogout) {
                Text("logout")
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