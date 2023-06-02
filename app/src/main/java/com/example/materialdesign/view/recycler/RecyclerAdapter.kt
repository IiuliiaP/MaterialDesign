package com.example.materialdesign.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityRecyclerItemEarthBinding
import com.example.materialdesign.databinding.ActivityRecyclerItemHeaderBinding
import com.example.materialdesign.databinding.ActivityRecyclerItemMarsBinding


class RecyclerAdapter(private var listData: MutableList<Pair<Data, Boolean>>,
                       val callbackAddMars : AddItem,
                       val callbackAddEarth: AddItem,
                       val callbackRemove : RemoveItem):
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(), ItemTouchHelperAdapter {

    fun setListDataRemove(listDataNew: MutableList<Pair<Data, Boolean>>, position: Int) {
        listData = listDataNew
        notifyItemRemoved(position)
    }

    fun setListDataAdd(listDataNew: MutableList<Pair<Data, Boolean>>, position: Int) {
        listData = listDataNew
        notifyItemInserted(position)
    }
    override fun getItemViewType(position: Int): Int {
        return listData[position].first.type
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return when(viewType){
            TYPE_EARTH ->{
                val binding = ActivityRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                EarthViewHolder(binding)
            }
            TYPE_MARS -> {
                val binding = ActivityRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                MarsViewHolder(binding)
            }

            else -> {
                val binding = ActivityRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(binding)
            }
        }
    }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(listData[position])

        }

        override fun getItemCount(): Int {
        return listData.size
        }
    abstract class MyViewHolder(view: View) :
            RecyclerView.ViewHolder(view), ItemTouchHelperViewHolder {
            abstract fun bind(data: Pair<Data, Boolean>)

            override fun onItemSelect() {
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.brown_light_jupiter
                    )
                )
            }
            override fun onItemClear() {
                itemView.setBackgroundColor(0)
            }
        }

   inner class EarthViewHolder(private val binding: ActivityRecyclerItemEarthBinding) :
        MyViewHolder(binding.root) {
        override fun bind(data: Pair<Data, Boolean>) {
            binding.name.text = data.first.name

            binding.earthItemAddImageView.setOnClickListener {
                callbackAddEarth.add(layoutPosition)
            }
            binding.earthItemDeleteImageView.setOnClickListener {
                callbackRemove.remove(layoutPosition)
            }
            binding.earthItemMoveUp.setOnClickListener {
                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition - 1, this)
                }
                notifyItemMoved(layoutPosition, layoutPosition - 1)
            }
            binding.earthItemMoveDown.setOnClickListener {
                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition + 1, this)
                }
                notifyItemMoved(layoutPosition, layoutPosition + 1)

            }
        }
    }

    inner class MarsViewHolder(private val binding: ActivityRecyclerItemMarsBinding) :
        MyViewHolder(binding.root){
        override fun bind(data: Pair<Data, Boolean>) {
            binding.name.text = data.first.name

            binding.marsItemAddImageView.setOnClickListener {
                callbackAddMars.add(layoutPosition)
            }
            binding.marsItemDeleteImageView.setOnClickListener {
                callbackRemove.remove(layoutPosition)
            }
            binding.marsItemMoveUp.setOnClickListener {
                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition - 1, this)
                }
                notifyItemMoved(layoutPosition, layoutPosition - 1)

            }
            binding.marsItemMoveDown.setOnClickListener {
                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition + 1, this)
                }
                notifyItemMoved(layoutPosition, layoutPosition + 1)
            }
        }
        }
    inner class HeaderViewHolder(private val binding: ActivityRecyclerItemHeaderBinding):
        MyViewHolder(binding.root){
        override fun bind(data: Pair<Data, Boolean>) {
            binding.name.text = data.first.name
        }

    }
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        callbackRemove.remove(position)
    }

    }