package com.sopt.now.presentation.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.databinding.FragmentSearchBinding
import com.sopt.now.presentation.FriendList.FriendListViewModel
import com.sopt.now.presentation.FriendList.FriendListAdapter
import com.sopt.now.presentation.FriendList.FriendListViewModelFactory

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FriendListAdapter
    private val viewModel: FriendListViewModel by viewModels { FriendListViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FriendListAdapter()

        viewModel.updateFriendList(2)  // 첫 페이지 로드
        with(binding.fragmentSearchRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SearchFragment.adapter
        }

        viewModel.friendList.observe(viewLifecycleOwner, { friendList ->
            adapter.submitList(friendList)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

