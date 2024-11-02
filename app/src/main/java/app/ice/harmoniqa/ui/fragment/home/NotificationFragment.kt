package app.ice.harmoniqa.ui.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.ice.harmoniqa.ui.screen.home.NotificationScreen
import app.ice.harmoniqa.ui.theme.AppTheme
import app.ice.harmoniqa.viewModel.NotificationViewModel

class NotificationFragment : Fragment() {
    private lateinit var composeView: ComposeView

    private val viewModel by viewModels<NotificationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {
            AppTheme {
                Scaffold { paddingValues ->
                    NotificationScreen(
                        navController = findNavController(),
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
