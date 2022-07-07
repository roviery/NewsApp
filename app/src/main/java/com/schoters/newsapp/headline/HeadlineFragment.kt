package com.schoters.newsapp.headline

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
import com.schoters.newsapp.databinding.FragmentHeadlineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlineFragment : Fragment() {

    private var _binding: FragmentHeadlineBinding? = null
    private val binding get() = _binding
    private val headlineViewModel: HeadlineViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeadlineBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter()

        if (activity != null) {
            requireActivity().findViewById<AppBarLayout>(R.id.main_appbar).visibility = View.VISIBLE
            requireActivity().findViewById<BottomNavigationView>(R.id.main_botnav).visibility =
                View.VISIBLE

            loadHeadlines()
            newsAdapter.onItemClick = { news ->
                val action =
                    HeadlineFragmentDirections.actionHeadlineFragmentToContentFragment(news.url, news)
                findNavController().navigate(action)
            }
        }
    }

    private fun loadHeadlines() {
        headlineViewModel.getHeadlinesNews("tech", isOnline(requireContext()))
            .observe(viewLifecycleOwner) { news ->
                Log.d("getNews", news.data.toString())
                if (news != null) {
                    newsAdapter.setData(news.data)
                }
            }

        with(binding?.headlineNewsRv) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = newsAdapter
        }
    }

}