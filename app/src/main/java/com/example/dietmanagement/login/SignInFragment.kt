package com.example.dietmanagement.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.dietmanagement.R

class SignInFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val finishBtn = view.findViewById<Button>(R.id.finish_button)
        finishBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_loginFragment)
        }

        return view
    }
}