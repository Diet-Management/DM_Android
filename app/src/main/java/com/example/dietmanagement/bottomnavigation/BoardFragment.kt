package com.example.dietmanagement.bottomnavigation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.CustomDialogSetStandardBinding
import com.example.dietmanagement.databinding.FragmentBoardBinding

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)

        binding.addPostButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_boardFragment_to_addPostActivity)
        }

        binding.settingStandard.setOnClickListener {
            setStandard()
        }
        return binding.root
    }

    // TODO :: 기준 작성 다이얼로그 만들기
    private fun setStandard() {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val dialogBinding: CustomDialogSetStandardBinding = CustomDialogSetStandardBinding.inflate(
            LayoutInflater.from(context))
        dialog.setContentView(dialogBinding.root)

        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogBinding.standardApply.setOnClickListener {
            val selectedRadio1 = dialogBinding.standardRadioGroup1.checkedRadioButtonId
            val selectedRadio2 = dialogBinding.standardRadioGroup2.checkedRadioButtonId

            Log.d("Diet Management", "setStandard selected radio button: $selectedRadio1, $selectedRadio2")

            val radioBtn1 = dialog.findViewById<View>(selectedRadio1) as RadioButton?
            val radioBtn2 = dialog.findViewById<View>(selectedRadio2) as RadioButton?

            Log.d("Diet Management", "setStandard: ${radioBtn1?.text}/${radioBtn2?.text}")

            binding.standardTextview.text = "${radioBtn1?.text}/${radioBtn2?.text}"
            dialog.dismiss()
        }

        dialogBinding.standardCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}