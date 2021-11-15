package ru.drankin.dev.githubapp.ui.repos

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.drankin.dev.githubapp.R
import ru.drankin.dev.githubapp.databinding.FragmentReposBinding
import ru.drankin.dev.githubapp.ui.base.NavigateTo
import ru.drankin.dev.githubapp.ui.main.ReposRecyclerViewAdapter


@AndroidEntryPoint
class ReposFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding : FragmentReposBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repos, container, false)
        val viewModel: ReposViewModel by viewModels()

        //Navigation
        viewModel.getNavigateLiveData().observe(this, Observer {
            when (it) {
//                NavigateTo.TO_BACK ->
            }
        })

        viewModel.getOnErrorLiveData().observe(this, Observer {
            if (it) {
                showAlertAndExit()
            }
        })

        //Transit params
        viewModel.setUserName(arguments?.getString("userName").toString())

        //Text fields
        viewModel.getTitle().observe(this, Observer {
            activity?.title = it
        })

        viewModel.getRepos().observe(this, Observer {
            val adapter = ReposRecyclerViewAdapter(it)
            binding.recyclerView.adapter = adapter
        })

        viewModel.getLoadingState().observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = ProgressBar.VISIBLE
            } else {
                binding.progressBar.visibility = ProgressBar.GONE
            }
        })

        return binding.root
    }

    fun showAlertAndExit(){
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.erroronreporequest))
            .setIcon(R.drawable.ic_baseline_error_24)
            .setPositiveButton("Ok") {
                dialog, id -> kotlin.run {
                    dialog.cancel()
                    findNavController().popBackStack()
                }
            }
            .create()
            .show()
    }
}
