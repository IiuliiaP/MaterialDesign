package com.example.materialdesign.view.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerBinding
    lateinit var adapter: RecyclerAdapter
    private val data = arrayListOf(
        Pair(Data(id=0,"Заголовок", type = TYPE_HEADER), false),
        Pair(Data(id=1,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=2,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=3,"Mars", type = TYPE_MARS), false),
        Pair(Data(id=4,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=5,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=6,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=7,"Mars", type = TYPE_MARS), false)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecyclerAdapter(data, callbackAddMars, callbackAddEarth, callbackRemove)
        binding.recyclerView.adapter = adapter

        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)

    }

    private val callbackAddEarth = AddItem {
        data.add(it, Pair(Data(0,"Earth(New)", type = TYPE_EARTH),false))
        adapter.setListDataAdd(data, it)
    }

    private val callbackAddMars = AddItem {
        data.add(it, Pair(Data(0,"Mars(New)", type = TYPE_MARS),false))
        adapter.setListDataAdd(data, it)
    }
    private val callbackRemove = RemoveItem {
        data.removeAt(it)
        adapter.setListDataRemove(data, it)
    }
}