package com.schoters.newsapp.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.schoters.core.ui.NewsAdapter
import com.schoters.core.utils.isOnline
import com.schoters.newsapp.R
import com.schoters.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter()

        if (activity != null) {
            requireActivity().findViewById<AppBarLayout>(R.id.main_appbar).visibility = View.VISIBLE
            requireActivity().findViewById<BottomNavigationView>(R.id.main_botnav).visibility =
                View.VISIBLE

            loadNews()
            newsAdapter.onItemClick = { news ->
                val action = HomeFragmentDirections.actionHomeFragmentToContentFragment(news.url, news)
                findNavController().navigate(action)
            }
        }

    }

    private fun loadNews() {
        homeViewModel.getNews(isOnline(requireContext()))
            .observe(viewLifecycleOwner) { news ->
                Log.d("getNews", news.data.toString())
                if (news != null) {
                    newsAdapter.setData(news.data)
                }
            }

        with(binding?.homeNewsRv) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = newsAdapter
        }
    }


}