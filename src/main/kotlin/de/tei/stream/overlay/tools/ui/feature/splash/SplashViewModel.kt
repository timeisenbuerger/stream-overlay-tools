package de.tei.stream.overlay.tools.ui.feature.splash

import de.tei.stream.overlay.tools.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val SPLASH_DELAY = 2000L // 2 seconds of splash delay
    }

    private val _isSplashFinished = MutableStateFlow(false)
    val isSplashFinished: StateFlow<Boolean> = _isSplashFinished

    override fun init(viewModelScope: CoroutineScope) {
        super.init(viewModelScope)

        viewModelScope.launch {
            delay(SPLASH_DELAY)
            _isSplashFinished.value = true
        }
    }
}