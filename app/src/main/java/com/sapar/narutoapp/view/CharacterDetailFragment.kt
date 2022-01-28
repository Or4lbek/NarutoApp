package com.sapar.narutoapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sapar.narutoapp.R
import com.sapar.narutoapp.databinding.CharacterDetailFragmentBinding
import com.sapar.narutoapp.model.CharactersItem
import com.sapar.narutoapp.viewmodel.CharacterDetailViewModel

class CharacterDetailFragment : Fragment(R.layout.character_detail_fragment) {

    private lateinit var viewModel: CharacterDetailViewModel
    private var _binding: CharacterDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CharacterDetailFragmentBinding.bind(view)

        val character: CharactersItem = args.character
        viewModel = ViewModelProvider(this)[CharacterDetailViewModel::class.java]
        updateUi(character)
    }

    private fun updateUi(character: CharactersItem) {
        Glide
            .with(requireActivity())
            .load(character.imageUrl)
            .centerCrop()
            .into(binding.imageViewLogo)

        with(binding) {
            textViewName.text = character.name
            textViewClanDetail.text = character.clan
            textViewOccupation.text = character.occupation.toString()
                .substring(1, character.occupation.toString().length - 1)
            textViewClassifaction.text = character.classifaction.toString()
                .substring(1, character.classifaction.toString().length - 1)
            textViewAgeDetail.text = character.age
            textViewBirthDetail.text = character.birthDate
            textViewBestJutsus.text = character.bestJutsus.toString()
                .substring(1, character.bestJutsus.toString().length - 1)
        }
    }
}