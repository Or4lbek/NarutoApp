package com.sapar.narutoapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sapar.narutoapp.R
import com.sapar.narutoapp.databinding.CharacterDetailFragmentBinding
import com.sapar.narutoapp.databinding.FragmentCharactersBinding
import com.sapar.narutoapp.model.CharactersItem
import com.sapar.narutoapp.viewmodel.CharacterDetailViewModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class CharacterDetailFragment : Fragment(R.layout.character_detail_fragment) {

    private lateinit var viewModel: CharacterDetailViewModel
    private var _binding: CharacterDetailFragmentBinding? = null
    private val binding get() = _binding!!
    val args:CharacterDetailFragmentArgs by navArgs()

    companion object {
        fun newInstance() = CharacterDetailFragment()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CharacterDetailFragmentBinding.bind(view)
        val character:CharactersItem = args.character
        updateUi(character)
    }

    private fun updateUi(character: CharactersItem) {
        Glide
            .with(requireActivity())
            .load(character.image_url)
            .centerCrop()
            .into(binding.imageViewLogo);
//        Picasso.with(requireContext())
//            .load(character.image_url)
//            .placeholder(R.drawable.back)
//            .fit()
//            .into(binding.imageViewLogo)
        binding.textViewName.text = character.name
        binding.textViewClanDetail.text = character.clan
        binding.textViewOccupation.text = character.occupation.toString().substring(1,character.occupation.toString().length-1)
        binding.textViewClassifaction.text = character.classifaction.toString().substring(1,character.classifaction.toString().length-1)
        binding.textViewAgeDetail.text = character.age
        binding.textViewBirthDetail.text = character.birthdate
        binding.textViewBestJutsus.text = character.best_jutsus.toString().substring(1,character.best_jutsus.toString().length-1)
    }

//    private fun arrayToString(array: ArrayList<String>?):String{
//        var text = ""
//        var index = 1
//        for (i in array!!){
//            text += i + " "
//            if (index == 1){
//                text += "\n"
//                index++
//                Toast.makeText(requireContext(), "mal", Toast.LENGTH_SHORT).show()
//
//            }
//        }
//        return text
//    }


}