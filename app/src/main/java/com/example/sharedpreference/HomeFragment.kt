package com.example.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.sharedpreference.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    private lateinit var binding : FragmentHomeBinding
    private lateinit var sharePref : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        sharePref = context?.getSharedPreferences("sharePref", Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        doLogout()
    }

    private fun getData(){
        val result = """
            ${sharePref.getString("fullname","undefined")}
        """.trimIndent()

        binding.tvResult.text = result

    }

    private fun doLogout(){
        binding.btnLogout.setOnClickListener {
            sharePref.edit().apply {
                clear()
                apply()
            }
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }
    }


}