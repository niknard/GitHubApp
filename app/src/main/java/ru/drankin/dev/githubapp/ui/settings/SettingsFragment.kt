package ru.drankin.dev.githubapp.ui.settings

import android.content.Context
import android.os.Bundle
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
import ru.drankin.dev.githubapp.databinding.FragmentReposBinding
import ru.drankin.dev.githubapp.databinding.FragmentSettingsBinding
import ru.drankin.dev.githubapp.ui.base.NavigateTo
import ru.drankin.dev.githubapp.ui.repos.ReposViewModel

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding : FragmentSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        val viewModel: SettingsViewModel by viewModels()

        //Navigate
        viewModel.getNavigateLiveData().observe(this, Observer {
            when (it){
                NavigateTo.TO_BACK ->
                    findNavController().popBackStack()
            }
        })

        //text fields
        binding.apiKey.setText(viewModel.getApiKey())

        //Click listerers
        binding.saveButton.setOnClickListener {
            viewModel.setApiKey(binding.apiKey.text.toString())
        }

        binding.cancelButton.setOnClickListener {
            viewModel.cancel()
        }


        return binding.root
    }
}