package com.example.campus_eats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        showStudentTypeDialog()
    }

    private fun showStudentTypeDialog() {
        val dialog = StudentTypeDialog(requireContext())
        dialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the ConstraintLayout with id JonesBtn
        val jonesBtn = view.findViewById<ConstraintLayout>(R.id.JonesBtn)
        val gatewayBtn = view.findViewById<ConstraintLayout>(R.id.GatewayBtn)

        // Set click listener on JonesBtn
        jonesBtn.setOnClickListener {
            // Replace current fragment with JonesFragment
            replaceFragment(JonesFragment())
        }

        // Set click listener on JonesBtn
        gatewayBtn.setOnClickListener {
            // Replace current fragment with JonesFragment
            replaceFragment(GatewayFragment())
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    // Other code...
}
