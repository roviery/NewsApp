package com.schoters.newsapp.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.schoters.core.ui.NewsAdapter
import com.schoters.core.utils.SwipeGesture
import com.schoters.newsapp.R
import com.schoters.newsapp.databinding.FragmentSavedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding
    private val savedViewModel: SavedViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter()

        if (activity != null) {
            requireActivity().findViewById<AppBarLayout>(R.id.main_appbar).visibility = View.VISIBLE
            requireActivity().findViewById<BottomNavigationView>(R.id.main_botnav).visibility =
                View.VISIBLE

            val swipeGesture = object : SwipeGesture(requireActivity()){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            savedViewModel.deleteSavedNews(newsAdapter.getData(viewHolder.adapterPosition))
                        }

                        ItemTouchHelper.RIGHT -> {
                            savedViewModel.deleteSavedNews(newsAdapter.getData(viewHolder.adapterPosition))
                        }
                    }
                }
            }
            val touchHelper = ItemTouchHelper(swipeGesture)
            touchHelper.attachToRecyclerView(binding?.savedNewsRv)

            loadSavedNews()
            newsAdapter.onItemClick = { news ->
                val action = SavedFragmentDirections.actionSavedFragmentToContentFragment(news.url, news)
                findNavController().navigate(action)
            }
        }
    }

    private fun loadSavedNews() {
        savedViewModel.getSavedNewsFromDB().observe(viewLifecycleOwner){ news ->
            if (news != null){
                newsAdapter.setData(news)
            }
        }

        with(binding?.savedNewsRv) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = newsAdapter
        }
    }

}