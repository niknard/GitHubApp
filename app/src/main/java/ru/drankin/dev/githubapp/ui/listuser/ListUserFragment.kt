package ru.drankin.dev.githubapp.ui.listuser

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.drankin.dev.githubapp.R
import ru.drankin.dev.githubapp.data.model.User
import ru.drankin.dev.githubapp.databinding.FragmentListUserBinding
import ru.drankin.dev.githubapp.ui.base.NavigateTo
import ru.drankin.dev.githubapp.ui.main.ListUserViewModel
import ru.drankin.dev.githubapp.ui.main.MainAdapterClickAction
import ru.drankin.dev.githubapp.ui.main.MainRecyclerViewAdapter
import javax.inject.Inject

@AndroidEntryPoint
class ListUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding : FragmentListUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_user, container, false)
        val viewModel: ListUserViewModel by viewModels()

        activity?.title=getString(R.string.app_name)

        val onUserClick = object : MainRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(action: MainAdapterClickAction, user: User) = when (action) {

                MainAdapterClickAction.EditButtonClick ->
                    viewModel.setNavigateLiveData(navigateTo = NavigateTo.TO_ADDUSER, bundleOf("id" to user.id))

                MainAdapterClickAction.DeleteButtonClick ->
                    viewModel.delUser(user.id?:0)

                MainAdapterClickAction.GoToRepo ->
                    viewModel.setNavigateLiveData(navigateTo = NavigateTo.TO_REPO, bundleOf("userName" to user.userName))
            }
        }

        //Recycler view
        viewModel.getUserList().observe(this, Observer{
            val adapter = MainRecyclerViewAdapter(it)
            adapter.setOnItemClickListener(onUserClick)
            binding.recyclerView.adapter = adapter
        })

        //Navigation events
        viewModel.getNavigateLiveData().observe(this, Observer {
            when (it) {
                NavigateTo.TO_ADDUSER -> findNavController().navigate(R.id.action_listUserFragment_to_addUserFragment, viewModel.bundle)
                NavigateTo.TO_REPO -> findNavController().navigate(R.id.action_listUserFragment_to_reposFragment, viewModel.bundle)
                NavigateTo.TO_SETTINGS -> findNavController().navigate(R.id.action_listUserFragment_to_settingsFragment, viewModel.bundle)
            }
        })

        //FAB
        binding.floatingActionButton.setOnClickListener {
            viewModel.setNavigateLiveData(navigateTo = NavigateTo.TO_ADDUSER)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val viewModel: ListUserViewModel by viewModels()

        return when (item.itemId) {
            R.id.settings -> {
                viewModel.setNavigateLiveData(NavigateTo.TO_SETTINGS)
                true
            }
            R.id.preparedb -> {
                viewModel.prepareDb()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}