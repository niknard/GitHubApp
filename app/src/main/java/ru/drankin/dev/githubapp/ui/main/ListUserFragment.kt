package ru.drankin.dev.githubapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.drankin.dev.githubapp.R
import ru.drankin.dev.githubapp.data.model.User
import ru.drankin.dev.githubapp.databinding.FragmentListUserBinding
import ru.drankin.dev.githubapp.tools.EventType

@AndroidEntryPoint
class ListUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding : FragmentListUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_user, container, false)
        val viewModel: ListUserViewModel by viewModels()

        viewModel.getUserList().observe(this, Observer{
            val adapter = MainRecyclerViewAdapter(it)

            adapter.setOnItemClickListener(object : MainRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(action: MainAdapterClickAction, user: User) {
                    when (action) {
                        MainAdapterClickAction.EditButtonClick ->
                            findNavController().navigate(R.id.action_listUserFragment_to_addUserFragment, bundleOf("id" to user.id))
                        MainAdapterClickAction.DeleteButtonClick ->
                            viewModel.delUser(user.id?:0)
                        MainAdapterClickAction.GoToRepo ->
                            findNavController().navigate(R.id.action_listUserFragment_to_reposFragment, bundleOf("userName" to user.userName))
                    }
                }
            })

            binding.recyclerView.adapter = adapter
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listUserFragment_to_addUserFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance() = ListUserFragment()
    }
}