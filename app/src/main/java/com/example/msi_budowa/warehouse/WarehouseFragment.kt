package com.example.msi_budowa.warehouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.msi_budowa.R
import com.example.msi_budowa.databinding.ItemWarehouseBinding
import com.example.msi_budowa.common.CategoryTree

import com.example.msi_budowa.common.Product
import com.example.msi_budowa.common.ProductCategory

class WarehouseFragment : Fragment(), AdapterView.OnItemSelectedListener
{
    lateinit var itemsLayout : LinearLayout
    lateinit var categoryComboBox : Spinner
    lateinit var subcategoryComboBox : Spinner
    lateinit var queryField : EditText

    val model : WarehouseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        model.DisplayedProducts.observe(viewLifecycleOwner) {warehouseItems ->
            UpdateLayoutItems(container, warehouseItems)
        }

        model.ChosenCategory.observe(viewLifecycleOwner) { category ->
            UpdateSubcategoryComboBox(model.CategoryTree.value!!.GetSubcategories(category))
        }

        model.ChosenSubcategory.observe(viewLifecycleOwner) { subcategory ->
            val adapter = (subcategoryComboBox.adapter as ArrayAdapter<ProductCategory>)
            val itemPos = adapter.getPosition(subcategory)
            subcategoryComboBox.setSelection(itemPos)
        }

        model.CategoryTree.observe(viewLifecycleOwner) { categoryTree ->
            UpdateCategoryComboBox(categoryTree)
        }

        return inflater.inflate(R.layout.fragment_warehouse, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsLayout = view.findViewById(R.id.itemsLayout)
        categoryComboBox = view.findViewById(R.id.categoryComboBox)
        subcategoryComboBox = view.findViewById(R.id.subcategoryComboBox)
        queryField = view.findViewById(R.id.queryField)

        categoryComboBox.onItemSelectedListener = this
        subcategoryComboBox.onItemSelectedListener = this

        queryField.addTextChangedListener { editable ->
            model.SetQuery(editable.toString())
        }
    }

    private fun UpdateLayoutItems(container: ViewGroup?, products : List<Product>){
        itemsLayout.removeAllViews()
        products.forEach { item ->
            val itemView = ItemWarehouseBinding.inflate(layoutInflater, itemsLayout, true)
            itemView.product = item
            itemView.root.setOnClickListener{
                val bundle = bundleOf("productId" to item.id)
                findNavController().navigate(R.id.action_warehouseFragment_to_productDetailsFragment, bundle)
            }
        }
    }

    private fun UpdateCategoryComboBox(categoryTree : CategoryTree){
        val categories = categoryTree.GetCategories()

        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
            .also{
                it.setDropDownViewResource(android.R.layout
                    .simple_spinner_dropdown_item);
                categoryComboBox.adapter = it
            }
    }

    private fun UpdateSubcategoryComboBox(subcategories : List<ProductCategory>){
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subcategories)
            .also{
                it.setDropDownViewResource(android.R.layout
                    .simple_spinner_dropdown_item);
                subcategoryComboBox.adapter = it
            }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        if(model.CategoryTree.value == null)
            return

        if(parent == categoryComboBox){
            if(parent.adapter != null){
                val chosenCategory = parent.getItemAtPosition(pos) as ProductCategory
                model.SetCategory(chosenCategory)
            }
        }else if(parent == subcategoryComboBox){
            if(parent.adapter != null){
                val chosenSubcategory = parent.getItemAtPosition(pos) as ProductCategory
                model.SetSubcategory(chosenSubcategory)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) { }

}