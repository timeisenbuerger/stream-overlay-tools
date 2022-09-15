package de.tei.stream.overlay.tools.di

import dagger.Component
import de.tei.stream.overlay.tools.ui.feature.main.MainScreenComponent
import de.tei.stream.overlay.tools.ui.feature.splash.SplashScreenComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Add your modules here
    ]
)
interface AppComponent {
    fun inject(splashScreenComponent: SplashScreenComponent)
    fun inject(mainScreenComponent: MainScreenComponent)
}