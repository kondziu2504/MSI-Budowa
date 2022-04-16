package com.example.msi_budowa.common.data_source

import com.example.msi_budowa.App
import com.example.msi_budowa.R
import com.example.msi_budowa.common.*

class MockDataSource : IDataSource {
    companion object{
        val constructionCategory = ProductCategory("Budowa")
        val woodSubcategory = ProductCategory("Drewno")
        val rockSubcategory = ProductCategory("Kamień")

        val decorationCategory = ProductCategory("Dekoracje")
        val paintsSubcategory = ProductCategory("Farby")
        val wallpapersSubcategory = ProductCategory("Tapety")
    }

    override fun GetWarehouseItems(onSuccess: (List<Product>) -> Unit) {
        onSuccess(
            listOf(
                Product("Deska", Price(900f, PriceType.PerItem), "Kawałek drewna", constructionCategory, woodSubcategory),
                Product("Deska2", Price(90f, PriceType.PerItem), "Kawałek drewna 2", constructionCategory, woodSubcategory),
                Product("Cegła", Price(34f, PriceType.PerItem),
                    App.context!!.resources.getString(R.string.text_placeholder_paragraph), constructionCategory, rockSubcategory
                ),
                Product("Farba", Price(25f, PriceType.PerMeter2),
                    App.context!!.resources.getString(R.string.text_placeholder_paragraph), decorationCategory, paintsSubcategory
                ),
                Product("Biała tapeta", Price(269f, PriceType.PerMeter2),
                    App.context!!.resources.getString(R.string.text_placeholder_paragraph), decorationCategory, wallpapersSubcategory
                ),
            )
        )
    }

    override fun GetCategories(onSuccess: (CategoryTree) -> Unit) {
        val categories = linkedMapOf<ProductCategory, List<ProductCategory>>(
            Pair(
                constructionCategory,
                listOf(
                    woodSubcategory,
                    rockSubcategory
                )
            ),
            Pair(
                decorationCategory,
                listOf(
                    paintsSubcategory,
                    wallpapersSubcategory
                )
            ),
        )

        onSuccess(CategoryTree(categories))
    }
}