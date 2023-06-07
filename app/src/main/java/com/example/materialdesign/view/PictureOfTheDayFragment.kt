package com.example.materialdesign.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.ScrollingMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.materialdesign.MainActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.example.materialdesign.view.recycler.RecyclerActivity
import com.example.materialdesign.view.viewpager.ViewPagerActivity
import com.example.materialdesign.viewmodel.AppState
import com.example.materialdesign.viewmodel.PictureOfTheDayViewModel


class PictureOfTheDayFragment : Fragment() {
    var isFlag = false

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private val viewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }
        viewModel.sendRequest()

        binding.textViewPictureExplanation.movementMethod = ScrollingMovementMethod.getInstance()

        pictureAnimation()

        wikiSearch(binding.inputLine.text.toString())

        changeTextView()

        setHasOptionsMenu(true)
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_picture_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.actionFavorite -> {
                activity?.let {
                    startActivity(Intent(it, ViewPagerActivity::class.java))
                }
            }
            R.id.actionSettings -> {
                requireActivity().supportFragmentManager.beginTransaction().hide(this)
                    .add(R.id.container, SettingsFragment.newInstance()).addToBackStack("").commit()

            }
            R.id.actionHome ->{
                activity?.let{
                    startActivity(Intent(it, RecyclerActivity::class.java))
                }
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun wikiSearch(searchText: String){
        binding.wikiInput.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${searchText}")
            })
        }
    }

    private fun changeTextView(){
        val text = binding.textViewPictureExplanation.text.toString()
        val spannableStringBuilder = SpannableStringBuilder(text)
        binding.textViewPictureExplanation.setText(spannableStringBuilder, TextView.BufferType.EDITABLE)
        for (i in text.indices){
            if(text[i]=='e'){
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(requireContext(),
                        androidx.appcompat.R.color.material_blue_grey_800)),
                    i,i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        spannableStringBuilder.setSpan(UnderlineSpan(),0,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    private fun renderData(appState: AppState){
        when(appState){
            is AppState.Error -> {

            }
            AppState.Loading -> {

            }
            is AppState.Success -> {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url)
                binding.textViewPictureExplanation.text = appState.pictureOfTheDayResponseData.explanation
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()
    }

    private fun pictureAnimation(){
        binding.imageView.setOnClickListener {
            isFlag = !isFlag
            val params = it.layoutParams as CoordinatorLayout.LayoutParams
            val transitionSet = TransitionSet()
            val changeImageTransform = ChangeImageTransform()
            val changeBounds = ChangeBounds()
            transitionSet.duration = 2000L

            transitionSet.addTransition(changeBounds)
            transitionSet.addTransition(changeImageTransform)

            TransitionManager.beginDelayedTransition(binding.root, transitionSet)
            if(isFlag){
                params.height = CoordinatorLayout.LayoutParams.MATCH_PARENT
                params.topMargin = 0
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.wikiInput.visibility = View.GONE
                binding.bottomAppBar.visibility = View.GONE
                binding.textViewPictureExplanation.visibility = View.GONE
            }else {
                params.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT
                params.topMargin = resources.getDimensionPixelSize(R.dimen.margin_top_picure_of_the_day)
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                binding.wikiInput.isVisible = true
                binding.bottomAppBar.isVisible = true
                binding.textViewPictureExplanation.isVisible = true
            }
            binding.imageView.layoutParams = params
        }

    }
}