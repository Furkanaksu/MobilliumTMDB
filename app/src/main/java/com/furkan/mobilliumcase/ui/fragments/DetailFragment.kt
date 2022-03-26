package com.furkan.mobilliumcase.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.furkan.mobilliumcase.R
import com.furkan.mobilliumcase.data.model.MoviesResult
import android.app.Activity
import android.graphics.Color
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.furkan.mobilliumcase.databinding.FragmentDetailBinding


class DetailFragment() : Fragment() {

    private lateinit var _binding : FragmentDetailBinding
    private val binding get() = _binding!!

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

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val arguments = arguments?.get("USER") as MoviesResult

        binding.let {
            Glide.with(requireContext())
                .load("http://image.tmdb.org/t/p/w500" + arguments.backdropPath)
                .centerCrop()
                .into(it.movieIcon)

            it.rate.text = arguments.voteAverage.toString()
            it.title.text = arguments.title.toString()
            it.date.text = arguments.releaseDate.toString()
            it.desc.text = arguments.overview.toString()

        }


        return  view
    }

    companion object {
        @JvmStatic
        fun newInstance(data: MoviesResult, activity: Activity ) {

            val navController = findNavController(activity, R.id.container)
            // Create the Bundle to pass, you can put String, Integer, or serializable object
            // Create the Bundle to pass, you can put String, Integer, or serializable object
            val bundle = Bundle()
            bundle.putSerializable("USER", data) // Serializable Object
            navController.navigate(R.id.action_main_to_detail, bundle) // called fragment with agruments
        }
    }
}