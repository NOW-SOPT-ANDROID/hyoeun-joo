package com.sopt.now.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.Friend.FriendAdapter
import com.sopt.now.User.UserAdapter
import com.sopt.now.databinding.FragmentHomeBinding



class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendAdapter = FriendAdapter()
        val userAdapter = UserAdapter()

        friendAdapter.setFriendList(viewModel.mockFriendList)
        userAdapter.setUserList(viewModel.mockUserList)
        val concatAdapter = ConcatAdapter(userAdapter, friendAdapter)

        binding.rvFriends.run {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}