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
import com.sapar.narutoapp.viewmodel.CharactersViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlin.collections.ArrayList
import com.github.ybq.android.spinkit.style.DoubleBounce

import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce


class CharactersFragment : Fragment(R.layout.fragment_characters),
    CharactersAdapter.OnItemNoteListener {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    var charactersList: ArrayList<CharactersItem> = ArrayList()
    lateinit var charactersAdapter: CharactersAdapter
    private lateinit var linearLayoutManager: GridLayoutManager
    var TAG = "Android"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharactersBinding.bind(view)

        initRecyclerView()
        initProgressBar()
    }

    private fun initRecyclerView() {
        linearLayoutManager = GridLayoutManager (requireContext(),2)
        binding.recyclerViewCharacters.layoutManager = linearLayoutManager
        charactersAdapter = CharactersAdapter(charactersList, requireContext(), this)
        binding.recyclerViewCharacters.adapter = charactersAdapter

        if (charactersList.isEmpty()){
            binding.progressBar.visibility = View.VISIBLE
            initViewModel()
        }
    }

    private fun initProgressBar(){
        val doubleBounce: Sprite = ThreeBounce()
        binding.progressBar.setIndeterminateDrawable(doubleBounce)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initViewModel() {
        Log.d(TAG, "initViewModel: ")

        val viewModel: CharactersViewModel =
            ViewModelProvider(this).get(CharactersViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if (it != null){
                binding.progressBar.visibility = View.GONE
                for(character in it){
                    charactersList.add(character)
                }
//                charactersList = it//ArrayList<CharactersItem>
                charactersAdapter.notifyDataSetChanged()
                Log.d(TAG, "list: "+it)
//                Toast.makeText(requireContext(),"Well in getting list...",Toast.LENGTH_SHORT).show()

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
        var character:CharactersItem = charactersList[position]
        val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character.name.toString(), character)
        findNavController().navigate(action)
    }
}