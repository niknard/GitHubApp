package ru.drankin.dev.githubapp.ui.main

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
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import ru.drankin.dev.githubapp.R
import ru.drankin.dev.githubapp.databinding.FragmentAddUserBinding
import ru.drankin.dev.githubapp.databinding.FragmentListUserBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AddUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AddUserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentAddUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user, container, false)

        val viewModel: AddUserViewModel by viewModels()

        if (arguments?.getInt("id") != null){
            val id: Int = arguments?.getInt("id")!!
            viewModel.loadUser(id)
            viewModel.getUser().observe(this, Observer {
                binding.editUserName.setText(it.userName)
            })
        }

        binding.saveButton.setOnClickListener {
            viewModel.setUserName(binding.editUserName.text.toString())
            viewModel.saveUser()
            findNavController().popBackStack()
        }

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddUserFragment()
    }
}