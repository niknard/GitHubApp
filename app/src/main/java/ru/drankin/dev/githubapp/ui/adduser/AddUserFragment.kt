package ru.drankin.dev.githubapp.ui.adduser

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
import ru.drankin.dev.githubapp.databinding.FragmentAddUserBinding
import ru.drankin.dev.githubapp.ui.base.NavigateTo



@AndroidEntryPoint
class AddUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding : FragmentAddUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user, container, false)
        val viewModel: AddUserViewModel by viewModels()

        //Navigate
        viewModel.getNavigateLiveData().observe(this, Observer {
            when (it){
                NavigateTo.TO_BACK ->
                    findNavController().popBackStack()
            }
        })

        //Text fields
        viewModel.getTitle().observe(this, Observer {
            activity?.title = it
        })

        viewModel.getUser().observe(this, Observer {
            binding.editUserName.setText(it.userName)
        })

        //Functional blocks
        viewModel.loadUser(arguments?.getInt("id"))

        //Click listerers
        binding.saveButton.setOnClickListener {
            viewModel.saveUser(binding.editUserName.text.toString())
        }

        binding.cancelButton.setOnClickListener {
            viewModel.cancel()
        }

        return binding.root
    }
}