package cy.com.core.interfaces

interface RecyclerItemData {

    fun isSameData(data: RecyclerItemData): Boolean {
        return this == data
    }
}