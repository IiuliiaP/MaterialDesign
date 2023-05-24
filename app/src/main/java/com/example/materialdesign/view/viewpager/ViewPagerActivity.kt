package com.example.materialdesign.view.viewpager

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.transition.TransitionManager
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {
    var isFlag = false
    private lateinit var binding: ActivityViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayoutViewPager.setupWithViewPager(binding.viewPager)

        binding.tabLayoutViewPager.getTabAt(EARTH)?.setIcon(R.drawable.ic_earh)
        binding.tabLayoutViewPager.getTabAt(MARS)?.setIcon(R.drawable.ic_mars)
        binding.tabLayoutViewPager.getTabAt(SPACE)?.setIcon(R.drawable.ic_space)

        binding.fabViewPagerActivity.setOnClickListener {
            isFlag = !isFlag
            TransitionManager.beginDelayedTransition(binding.root)
            if(isFlag){
                ObjectAnimator.ofFloat(binding.fabViewPagerActivity, View.ROTATION, 0f,720f)
                    .setDuration(1000L).start()
                binding.tabLayoutViewPager.visibility = View.GONE

            }else{
                ObjectAnimator.ofFloat(binding.fabViewPagerActivity, View.ROTATION, 720f,0f)
                    .setDuration(1000L).start()
                binding.tabLayoutViewPager.isVisible = true
            }
        }

    }
    companion object{
        private const val EARTH = 0
        private const val MARS = 1
        private const val SPACE = 2

    }
}