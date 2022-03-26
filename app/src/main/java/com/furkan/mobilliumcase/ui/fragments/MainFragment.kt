package com.furkan.mobilliumcase.ui.fragments

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkan.mobilliumcase.R
import com.furkan.mobilliumcase.databinding.FragmentMainBinding
import com.furkan.mobilliumcase.ui.adapters.SliderAdapter
import com.furkan.mobilliumcase.ui.viewmodels.MainFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.PagerSnapHelper

import androidx.recyclerview.widget.SnapHelper
import com.furkan.mobilliumcase.ui.adapters.VerticalAdapter
import com.furkan.mobilliumcase.utils.NotificationBar


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.window.apply {
            this!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        getData()

        val snapHelper: SnapHelper = PagerSnapHelper()
        binding.rcycSlider.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        snapHelper.attachToRecyclerView(binding.rcycSlider)


        binding.rcycHorizontal.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

    }

    private fun getData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllDiscover()
        }
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllNews()
        }

        viewModel.getMovies.observe(viewLifecycleOwner, {
            it?.let {
                Log.d("denemeData", it.toString())
                binding.rcycSlider.adapter = SliderAdapter(it.results){
                    DetailFragment.newInstance(it, requireActivity())
                }
                binding.indicator.attachToRecyclerView(binding.rcycSlider);
            }
        })

        viewModel.getNewMovies.observe(viewLifecycleOwner, {
            it?.let {
                Log.d("denemeData", it.toString())
                binding.rcycHorizontal.adapter = VerticalAdapter(it.results){
                    DetailFragment.newInstance(it, requireActivity())
                }
            }
        })

    }
}