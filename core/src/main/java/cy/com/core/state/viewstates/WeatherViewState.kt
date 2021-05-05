package cy.com.core.state.viewstates

import cy.com.core.interfaces.RecyclerViewItem
import cy.com.core.state.interfaces.ViewState

data class WeatherViewState(
    val recycleViewItems: List<RecyclerViewItem> = listOf(),
    val showProgressBar: Boolean = true,
    override val error: Throwable? = null
) : ViewState()