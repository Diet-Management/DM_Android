package com.example.dietmanagement.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val nextBtn = view.findViewById<Button>(R.id.next_btn)
        nextBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_signInFragment)
        }

        return view
    }
}