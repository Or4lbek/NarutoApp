package com.sapar.narutoapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sapar.narutoapp.R
import com.sapar.narutoapp.databinding.FragmentCharactersBinding
import com.sapar.narutoapp.model.CharactersItem
import com.sapar.narutoapp.view.adapter.CharactersAdapter
import com.sapar.narutoapp.viewmodel.CharactersFragmentViewModel
import java.util.*
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import kotlin.collections.ArrayList

class CharactersFragment : Fragment(R.layout.fragment_characters),
    CharactersAdapter.OnItemNoteListener {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    var charactersList: List<CharactersItem> = ArrayList()
    lateinit var charactersAdapter: CharactersAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    var TAG = "Android"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharactersBinding.bind(view)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCharacters.layoutManager = linearLayoutManager
        charactersAdapter = CharactersAdapter(charactersList, requireContext(), this)
        binding.recyclerViewCharacters.adapter = charactersAdapter

        if (charactersList.isEmpty()){
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initViewModel() {
        Log.d(TAG, "initViewModel: ")

        val viewModel: CharactersFragmentViewModel =
            ViewModelProvider(this).get(CharactersFragmentViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            Log.d(TAG, "initViewModel: "+it)
            if (it != null){
                binding.progressBar.visibility = View.GONE
                charactersList = it//ArrayList<CharactersItem>
                charactersAdapter.notifyDataSetChanged()
                Toast.makeText(requireContext(),"Well in getting list...",Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(requireContext(),"Error in getting list...",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }

    override fun onNoteClick(position: Int) {
        TODO("Not yet implemented")
    }
}