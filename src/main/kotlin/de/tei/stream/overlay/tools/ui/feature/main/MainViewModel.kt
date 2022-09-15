package de.tei.stream.overlay.tools.ui.feature.main

import de.tei.stream.overlay.tools.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MainViewModel @Inject constructor(
) : ViewModel() {

    override fun init(viewModelScope: CoroutineScope) {
        super.init(viewModelScope)
        this.viewModelScope = viewModelScope
    }

    lateinit var viewModelScope: CoroutineScope
}