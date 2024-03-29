package com.sapar.narutoapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.sapar.narutoapp.R
import com.sapar.narutoapp.databinding.FragmentCharactersBinding
import com.sapar.narutoapp.model.CharactersItem
import com.sapar.narutoapp.view.adapter.CharactersAdapter
import com.sapar.narutoapp.viewmodel.CharactersViewModel


class CharactersFragment : Fragment(R.layout.fragment_characters),
    CharactersAdapter.OnItemNoteListener {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var linearLayoutManager: GridLayoutManager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharactersBinding.bind(view)

        initRecyclerView()
        initProgressBar()
    }

    private fun initRecyclerView() {
        linearLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewCharacters.layoutManager = linearLayoutManager
        charactersAdapter = CharactersAdapter(requireContext(), this)
        binding.recyclerViewCharacters.adapter = charactersAdapter

        if (charactersAdapter.items.isEmpty()) {
            binding.progressBar.visibility = View.VISIBLE
            initViewModel()
        }
    }

    private fun initProgressBar() {
        val doubleBounce: Sprite = ThreeBounce()
        binding.progressBar.setIndeterminateDrawable(doubleBounce)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initViewModel() {
        val viewModel: CharactersViewModel =
            ViewModelProvider(this)[CharactersViewModel::class.java]
        viewModel.getCharactersObserver().observe(this, {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                charactersAdapter.items = it
            } else {
                Toast.makeText(requireContext(), "Error in getting list...", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.makeApiCall()
    }



    override fun onNoteClick(position: Int) {
        val character: CharactersItem = charactersAdapter.items[position]
        val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(
            character.name.toString(),
            character
        )
        findNavController().navigate(action)
    }
}