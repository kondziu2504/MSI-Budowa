package com.example.msi_budowa.warehouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.example.msi_budowa.R
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.common.data_source.Repository
import com.example.msi_budowa.databinding.FragmentWarehouseItemDetailsBinding
import com.example.msi_budowa.orders.Order
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.max

class WarehouseDetailsFragment : Fragment() {

    lateinit var binding : FragmentWarehouseItemDetailsBinding
    lateinit var quantityText : TextView
    lateinit var increaseAmountBtn : AppCompatImageButton
    lateinit var decreaseAmountBtn : AppCompatImageButton
    lateinit var addBtn : Button

    var product : Product? = null

    private var chosenAmount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWarehouseItemDetailsBinding.inflate(inflater)
        val view = binding.root

        quantityText = view.findViewById(R.id.quantity)
        increaseAmountBtn = view.findViewById(R.id.increaseAmountBtn)
        decreaseAmountBtn = view.findViewById(R.id.decreaseAmountBtn)
        addBtn = view.findViewById(R.id.addBtn)

        quantityText.text = chosenAmount.toString()

        increaseAmountBtn.setOnClickListener{
            chosenAmount++
            quantityText.text = chosenAmount.toString()
        }

        decreaseAmountBtn.setOnClickListener{
            chosenAmount = max(chosenAmount - 1, 0)
            quantityText.text = chosenAmount.toString()
        }

        addBtn.setOnClickListener{
            if(product == null)
                return@setOnClickListener

            val order = requireActivity().intent.getParcelableExtra<Order>("order")

            val newOrder = order!!.copy()
            if(!newOrder.products.containsKey(product!!.id))
                newOrder.products[product!!.id] = chosenAmount
            else
                newOrder.products[product!!.id] = newOrder.products[product!!.id]!! + chosenAmount

            lifecycle.coroutineScope.launch { withContext(Dispatchers.IO) {
                Repository.UpdateOrder(
                    newOrder,
                    onSuccess = { Snackbar.make(requireView(), "Dodano materiały", Snackbar.LENGTH_SHORT).show()},
                    onFailure = { Snackbar.make(requireView(), "Nie udało się zaktualizować materiałów", Snackbar.LENGTH_SHORT).show()}
                )
            } }
        }

        val productId = requireArguments().getLong("productId")

        lifecycle.coroutineScope.launch{ withContext(Dispatchers.IO) {
            Repository.GetWarehouseItems { items ->
                launch { withContext(Dispatchers.Main) {
                    product = items.find { item -> item.id == productId }
                    binding.product = product
                } }
            }
        } }

        return view
    }

}