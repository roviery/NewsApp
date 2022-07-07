package com.schoters.newsapp.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.schoters.newsapp.R
import com.schoters.newsapp.databinding.FragmentContentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding
    private val contentViewModel: ContentViewModel by viewModels()
    private val args: ContentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<AppBarLayout>(R.id.main_appbar).visibility = View.GONE
        requireActivity().findViewById<BottomNavigationView>(R.id.main_botnav).visibility =
            View.GONE
        val url = args.url
        val news = args.news

        if (activity != null) {
            binding?.contentWebView?.webViewClient = WebViewClient()
            if (url != null)
                binding?.contentWebView?.loadUrl(url)
            binding?.contentWebView?.settings?.javaScriptEnabled = true
            binding?.contentWebView?.settings?.setSupportZoom(true)

            binding?.contentFab?.setOnClickListener {
                contentViewModel.insertSavedNews(news)
                Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show()
            }
        }
    }


}