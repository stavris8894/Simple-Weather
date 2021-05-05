package cy.com.core.interfaces


interface RecyclerViewItem {

    val itemType: RecyclerViewItemType

    val id: Any

    val data: Any
}