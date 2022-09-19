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
import com.example.sharedpreference.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var sharePref : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentLoginBinding.inflate(layoutInflater)
        sharePref = context?.getSharedPreferences("sharePref", Context.MODE_PRIVATE)!!
       return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register()
        doLogin()
    }


    private fun doLogin(){
        binding.btnSave.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val usernames = sharePref.getString("username","undefined")
            val passwords = sharePref.getString("secondPassword","undefined")
            if(username.isNotBlank() && password.isNotBlank()){
                if(username.equals(usernames) && password.equals(passwords)){
                    Navigation.findNavController(binding.root).navigate(R.id.homeFragment)
                }else{
                    Toast.makeText(requireActivity(), "Username atau password anda salah", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireActivity(), "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun register(){
        binding.tvRegister.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.registerFragment)
        }
    }

}