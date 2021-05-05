package cy.com.core.interfaces

abstract class RecyclerViewUiItem<T : ViewHolderId>(
    override val id: T
) : RecyclerViewItem
