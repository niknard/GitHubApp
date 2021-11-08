package ru.drankin.dev.githubapp.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.drankin.dev.githubapp.R
import ru.drankin.dev.githubapp.databinding.FragmentListUserBinding
import ru.drankin.dev.githubapp.databinding.FragmentReposBinding


@AndroidEntryPoint
class ReposFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding : FragmentReposBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repos, container, false)
        val viewModel: ReposViewModel by viewModels()

        viewModel.getOnErrorLiveData().observe(this, Observer {
            if (it) {
                showAlertAndExit()
            }
        })

        val userName = arguments?.getString("userName").toString()

        viewModel.getRepos(userName).observe(this, Observer {
            val adapter = ReposRecyclerViewAdapter(it)
            binding.recyclerView.adapter = adapter
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
