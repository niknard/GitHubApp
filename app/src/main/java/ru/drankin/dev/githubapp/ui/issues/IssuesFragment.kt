package ru.drankin.dev.githubapp.ui.issues

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
import ru.drankin.dev.githubapp.data.model.Repo
import ru.drankin.dev.githubapp.databinding.FragmentIssuesBinding
import ru.drankin.dev.githubapp.ui.main.IssuesRecyclerViewAdapter
import ru.drankin.dev.githubapp.ui.main.ReposRecyclerViewAdapter

@AndroidEntryPoint
class IssuesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentIssuesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        val viewModel: IssuesViewModel by viewModels()

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
        viewModel.setRepo(arguments?.getString("url").toString())

        //Text fields
        viewModel.getTitle().observe(this, Observer {
            activity?.title = it
        })

        viewModel.getIssues().observe(this, Observer {
            val adapter = IssuesRecyclerViewAdapter(it)
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
            .setTitle(getString(R.string.erroronissuesrequest))
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