package com.example.dietmanagement.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.dietmanagement.R

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val newUserBtn = view.findViewById<Button>(R.id.new_user_btn)
        newUserBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signInFragment)
        }

        return view
    }
}