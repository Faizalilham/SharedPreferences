package com.example.sharedpreference.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.sharedpreference.R
import com.example.sharedpreference.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private lateinit var sharePref : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        sharePref = context?.getSharedPreferences("sharePref", Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doRegister()
    }


    private fun doRegister(){
        binding.btnRegister.setOnClickListener {
            val fullname = binding.etFullname.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val firstPassword = binding.etPassword.text.toString().trim()
            val secondPassword = binding.etSecondPassword.text.toString().trim()

            if(fullname.isNotBlank() && username.isNotBlank() && firstPassword.isNotBlank() && secondPassword.isNotBlank()){
                if(firstPassword.equals(secondPassword)){
                    sharePref.edit().apply{
                        putString("fullname",fullname)
                        putString("username",username)
                        putString("firstPassword",firstPassword)
                        putString("secondPassword",secondPassword)
                        apply()
                    }
                    Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
                }else{
                    Toast.makeText(requireActivity(), "Password anda tidak sama, Tolong cek kembali", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(requireActivity(), "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }


}