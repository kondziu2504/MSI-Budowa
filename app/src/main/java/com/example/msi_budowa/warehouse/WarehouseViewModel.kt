package com.example.msi_budowa.warehouse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msi_budowa.common.CategoryTree
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.common.ProductCategory
import com.example.msi_budowa.common.data_source.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WarehouseViewModel : ViewModel() {
    val DisplayedProducts : LiveData<List<Product>>  get() = displayedProducts
    val CategoryTree : LiveData<CategoryTree> get() = categoryTree
    val ChosenCategory : LiveData<ProductCategory> get() = chosenCategory
    val ChosenSubcategory : LiveData<ProductCategory> get() = chosenSubcategory
    private val chosenCategory = MutableLiveData<ProductCategory>()
    private val chosenSubcategory = MutableLiveData<ProductCategory>()

    private var allProducts : List<Product> = emptyList()
    private var categoryTree = MutableLiveData<CategoryTree>()
    private val displayedProducts = MutableLiveData<List<Product>>()
    private var query : String = ""

    fun SetCategory(category: ProductCategory){
        chosenCategory.value = category
        chosenSubcategory.value = categoryTree.value!!.GetSubcategories(category).first()
        UpdateDisplayedProducts()
    }

    fun SetSubcategory(subcategory : ProductCategory){
        chosenSubcategory.value = subcategory
        UpdateDisplayedProducts()
    }

    fun SetQuery(query : String){
        this.query = query
        UpdateDisplayedProducts()
    }

    init {
        LoadAllProducts()
        LoadCategories()
    }

    private fun LoadAllProducts(){
        viewModelScope.launch { withContext(Dispatchers.IO) {
            Repository.GetWarehouseItems { products ->
                launch { withContext(Dispatchers.Main) {
                    allProducts = products
                    UpdateDisplayedProducts()
                }}
            }}
        }
    }

    private fun LoadCategories(){
        viewModelScope.launch { withContext(Dispatchers.IO){
            Repository.GetCategories { categoryTree ->
                launch { withContext(Dispatchers.Main) {
                    this@WarehouseViewModel.categoryTree.value = categoryTree
                }}
            }
        }}
    }

    private fun UpdateDisplayedProducts(){
        displayedProducts.value = allProducts.filter {
            ProductMatchesCategories(it) && ProductMatchesQuery(it)
        }
    }

    private fun ProductMatchesCategories(product : Product) : Boolean {
        if(chosenCategory.value == null)
            return true
        else if (product.category == null || product.subcategory == null) {
            return false
        }else{
            val matchesCategory = product.category == chosenCategory.value
            val matchesSubcategory = product.subcategory == chosenSubcategory.value
            return matchesCategory && matchesSubcategory
        }
    }

    private fun ProductMatchesQuery(product : Product) : Boolean {
        if(query != "")
            return product.name.contains(query, ignoreCase = true)
        else
            return true
    }
}