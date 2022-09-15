package de.tei.stream.overlay.tools.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
import com.arkivanov.essenty.parcelable.Parcelable
import de.tei.stream.overlay.tools.di.AppComponent
import de.tei.stream.overlay.tools.ui.feature.main.MainScreenComponent
import de.tei.stream.overlay.tools.ui.feature.splash.SplashScreenComponent

/**
 * All navigation decisions are made from here
 */
class NavHostComponent(
    private val componentContext: ComponentContext,
) : Component, ComponentContext by componentContext {

    private lateinit var splashScreenComponent: SplashScreenComponent
    private lateinit var mainScreenComponent: MainScreenComponent

    /**
     * Available screensSelectApp
     */
    private sealed class Config : Parcelable {
        object Splash : Config()
        object Main : Config()
    }

    private val appComponent: AppComponent = de.tei.stream.overlay.tools.di.DaggerAppComponent.create()

    /**
     * Router configuration
     */
    private val router = router<Config, Component>(
        initialConfiguration = Config.Splash,
        childFactory = ::createScreenComponent,
        handleBackButton = true
    )

    /**
     * When a new navigation request made, the screen will be created by this method.
     */
    private fun createScreenComponent(config: Config, componentContext: ComponentContext): Component {
        initComponents()

        return when (config) {
            is Config.Splash -> splashScreenComponent
            is Config.Main -> mainScreenComponent
        }
    }

    private fun initComponents() {
        if (!::splashScreenComponent.isInitialized) {
            splashScreenComponent = SplashScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                onSplashFinished = ::onSplashFinished,
            )
        }

        if (!::mainScreenComponent.isInitialized) {
            mainScreenComponent = MainScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
            )
        }
    }

    @OptIn(ExperimentalDecomposeApi::class)
    @Composable
    override fun render() {
        Children(
            routerState = router.state,
            animation = crossfadeScale()
        ) { child ->
            child.instance.render()
        }
    }

    /**
     * Invoked when splash finish data sync
     */
    private fun onSplashFinished() {
        router.replaceCurrent(Config.Main)
    }
}