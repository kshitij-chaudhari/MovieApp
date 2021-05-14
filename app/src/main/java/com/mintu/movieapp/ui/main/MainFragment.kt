package com.mintu.movieapp.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintu.movieapp.R
import com.mintu.movieapp.databinding.MainFragmentBinding
import com.mintu.movieapp.ui.adapter.MoviesListAdapter
import com.mintu.movieapp.util.isConnected
import com.mintu.movieapp.util.toast
import com.mintu.presentation.model.Data
import com.mintu.presentation.viewmodel.MoviesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MoviesListViewModel by viewModels()
    private lateinit var movieAdaper: MoviesListAdapter
    private lateinit var fragmentBinding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding = MainFragmentBinding.bind(view)

        viewModel.getMoviesList(currentTime = System.currentTimeMillis(), isConnected = isConnected)
        initView(view.context)
        setupObserver()
        setupMenuListeners()
    }

    private fun initView(context: Context) {
        movieAdaper = MoviesListAdapter(emptyList())
        fragmentBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdaper
        }
    }

    private fun setupObserver() {
        viewModel.moviesListState.onEach {
            updateView(data = it)
        }.launchIn(lifecycleScope)

        viewModel.loadingState.onEach {
            fragmentBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }.launchIn(lifecycleScope)

        viewModel.errorState.onEach { error ->
            if(error.isNotBlank()) {
                toast(error)
            }
        }.launchIn(lifecycleScope)
    }

    private fun updateView(data: List<Data>) {
        movieAdaper.setNewItems(data)
    }

    private fun setupMenuListeners() {
        fragmentBinding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_search -> {
                    val searchView = menuItem.actionView as SearchView
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextChange(newText: String?): Boolean {
                            if(newText?.isBlank() == true) {
                                viewModel.getMoviesList(currentTime = System.currentTimeMillis(), isConnected = isConnected)
                            }
                            return false
                        }

                        override fun onQueryTextSubmit(query: String?): Boolean {
                            viewLifecycleOwner.lifecycleScope.launch {
                                if(query?.isNotBlank() == true) {
                                    viewModel.getMoviesList(query)
                                }
                            }
                            return false
                        }
                    })
                    true
                }
                else -> false
            }
        }
    }
}