package com.example.materialdesign.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.MainActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentSettingsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = activity as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0-> parentActivity.setTheme(R.style.JupiterTheme)
                    1-> parentActivity.setTheme( R.style.MoonTheme)
                }

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.imageView, PictureOfTheDayFragment.newInstance()).addToBackStack("").commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if(tab?.position==0){
                    parentActivity.setTheme(R.style.MoonTheme)

                }else parentActivity.setTheme(R.style.JupiterTheme)

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if(tab?.position==0) {
                    parentActivity.setTheme(R.style.JupiterTheme)
                }

            }
        })


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance() = SettingsFragment()
    }

}