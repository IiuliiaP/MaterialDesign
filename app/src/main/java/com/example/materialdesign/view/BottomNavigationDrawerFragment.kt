package com.example.materialdesign.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentBottomNavigationDrawerBinding


class BottomNavigationDrawerFragment : Fragment() {

    private var _binding: FragmentBottomNavigationDrawerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomNavigationDrawerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigationOne -> Toast.makeText(context, "${R.string.show_screen1}", Toast.LENGTH_SHORT).show()
                R.id.navigationTwo -> Toast.makeText(context, "${R.string.show_screen2}", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = BottomNavigationDrawerFragment()
    }
}