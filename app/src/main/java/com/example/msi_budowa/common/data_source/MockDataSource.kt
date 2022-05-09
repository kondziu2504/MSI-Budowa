package com.example.msi_budowa.common.data_source

import com.example.msi_budowa.App
import com.example.msi_budowa.R
import com.example.msi_budowa.common.*
import com.example.msi_budowa.orders.Order
import com.example.msi_budowa.orders.OrderStatus

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
                Product(0, "Deska", Price(900f, PriceType.PerItem), "Kawałek drewna", constructionCategory, woodSubcategory),
                Product(1,"Deska2", Price(90f, PriceType.PerItem), "Kawałek drewna 2", constructionCategory, woodSubcategory),
                Product(2,"Cegła", Price(34f, PriceType.PerItem),
                    App.context!!.resources.getString(R.string.text_placeholder_paragraph), constructionCategory, rockSubcategory
                ),
                Product(3,"Farba", Price(25f, PriceType.PerMeter2),
                    App.context!!.resources.getString(R.string.text_placeholder_paragraph), decorationCategory, paintsSubcategory
                ),
                Product(4,"Biała tapeta", Price(269f, PriceType.PerMeter2),
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

    override fun GetOrders(onSuccess: (List<Order>) -> Unit) {
        var orders =
            listOf(
                Order(1,"a", "b",
                    "Pasaż Grunwaldzki, Wrocław",
                    OrderStatus.InProcess, "d",
                    mutableMapOf(Pair(0, 52), Pair(1, 423), Pair(2, 3))
                ),
                Order(2, "Tytuł zlecenia", "Jacek Jędruszek",
                    "osiedle Korczak, Kalisz", OrderStatus.InProcess,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut suscipit dolor sed mi condimentum bibendum. Etiam sed arcu at est elementum molestie vitae eu metus. Nulla sem leo, tincidunt eget risus eu, commodo vestibulum augue. Nullam venenatis mauris metus, at porta odio tincidunt at. Ut ac arcu eu mi porta tincidunt. Etiam lobortis magna vel nulla molestie, ut fermentum eros suscipit. Nam commodo, lectus sed tempor pellentesque, turpis dolor mattis mi, vel dignissim leo lorem at arcu.",
                    mutableMapOf(Pair(0, 512), Pair(2, 33))),
                Order(3, "Remont kuchni", "Konrad Stręk",
                    "Cicha 5, Pleszew", OrderStatus.NotStarted,
                    "Położyć nowe płytki", mutableMapOf(Pair(1, 423), Pair(2, 433)))
            )

        onSuccess(orders)
    }

    override fun UpdateOrder(onSuccess: () -> Unit, onFailure: () -> Unit) {
        onSuccess()
    }
}