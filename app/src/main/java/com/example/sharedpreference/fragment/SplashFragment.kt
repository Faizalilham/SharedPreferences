package com.example.sharedpreference.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.sharedpreference.R
import com.example.sharedpreference.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
   private lateinit var binding : FragmentSplashBinding
   private lateinit var sharePref : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding = FragmentSplashBinding.inflate(layoutInflater)
      sharePref = context?.getSharedPreferences("sharePref", Context.MODE_PRIVATE)!!
      return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            isLogin()
        }, 1500)

    }

    private fun isLogin(){
        val usernames = sharePref.getString("username","undefined")
        if(!usernames.equals("undefined")){
            Navigation.findNavController(binding.root).navigate(R.id.homeFragment)
        }else{
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }
    }
}