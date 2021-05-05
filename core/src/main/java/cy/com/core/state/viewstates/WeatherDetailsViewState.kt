package cy.com.core.state.viewstates

import cy.com.core.interfaces.RecyclerViewItem
import cy.com.core.state.interfaces.ViewState

data class WeatherDetailsViewState(
    val weatherData: List<RecyclerViewItem> = listOf(),
    override val error: Throwable? = null
) : ViewState()