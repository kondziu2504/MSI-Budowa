package com.example.msi_budowa.building_products;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.msi_budowa.R

class ProductsListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_Second2Fragment)
        }*/
    }
}