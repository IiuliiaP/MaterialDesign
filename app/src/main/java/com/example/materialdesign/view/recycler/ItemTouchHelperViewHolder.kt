package com.example.materialdesign.view.recycler

interface ItemTouchHelperViewHolder {
    fun onItemSelect()
    fun onItemClear()
}
interface  ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}