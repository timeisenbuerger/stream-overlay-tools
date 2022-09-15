package de.tei.stream.overlay.tools.ui.feature

import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.theapache64.cyclone.core.Activity
import com.theapache64.cyclone.core.Intent
import de.tei.stream.overlay.tools.App
import de.tei.stream.overlay.tools.ui.navigation.NavHostComponent
import de.tei.stream.overlay.tools.ui.value.StreamOverlayToolsAppTheme
import androidx.compose.ui.window.application as setContent

/**
 * The activity who will be hosting all screens in this app
 */
class MainActivity : Activity() {
    companion object {
        fun getStartIntent(): Intent {
            return Intent(MainActivity::class).apply {
                // data goes here
            }
        }

        lateinit var windowInstance: ComposeWindow
    }

    override fun onCreate() {
        super.onCreate()

        // Igniting navigation, like https://github.com/arkivanov/Decompose/blob/master/sample/master-detail/app-desktop/src/jvmMain/kotlin/com/arkivanov/masterdetail/app/Main.kt
        val lifecycle = LifecycleRegistry()
        val navHostComponent = NavHostComponent(DefaultComponentContext(lifecycle))

        setContent {
            Window(
                onCloseRequest = ::exitApplication,
                title = "${App.appArgs.appName} (${App.appArgs.version})",
                icon = painterResource("drawables/launcher_icons/system.png"),
                state = rememberWindowState(placement = WindowPlacement.Floating),
            ) {
                windowInstance = this.window

                StreamOverlayToolsAppTheme {
                    // render root component
                    navHostComponent.render()
                }
            }
        }
    }
}