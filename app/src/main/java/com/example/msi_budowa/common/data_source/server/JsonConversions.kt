package com.example.msi_budowa.common.data_source.server

import com.example.msi_budowa.common.*
import com.example.msi_budowa.notes.Note
import com.example.msi_budowa.orders.Order
import com.example.msi_budowa.orders.OrderStatus
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class OrderDeserializer : JsonDeserializer<Order> {

    private fun orderStatusFromString(statusStr : String) : OrderStatus {
        if(statusStr == "inProcess")
            return OrderStatus.InProcess
        else if(statusStr == "inProcess")
            return OrderStatus.NotStarted
        else
            return OrderStatus.NotStarted
    }

    private fun orderStatusFromInt(statusStr : Int) : OrderStatus {
        if(statusStr == 2)
            return OrderStatus.InProcess
        else if(statusStr == 1)
            return OrderStatus.NotStarted
        else
            return OrderStatus.NotStarted
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Order {
        val orderJson = json!!.asJsonObject

        val id: Long = orderJson.get("id").asLong
        val title: String? = orderJson.get("title").asString
        val orderingName: String? = orderJson.get("name").asString
        val address: String? = orderJson.get("address").asString
        val status: OrderStatus = orderStatusFromInt(orderJson.get("status").asInt)
        val description: String? = orderJson.get("description").asString
        val mapType = object : TypeToken<MutableMap<Long, Int>>() {}.type
        val products : MutableMap<Long, Int> = GsonBuilder().create().fromJson(orderJson.get("products"), mapType)

        return Order(id, title, orderingName, address, status, description, products)
    }
}

class NoteDeserializer : JsonDeserializer<Note> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Note {
        val orderJson = json!!.asJsonObject

        val orderId: Long = orderJson.get("orderId").asLong
        val noteId: Long = orderJson.get("noteId").asLong
        val title: String? = orderJson.get("title").asString
        val description: String? = orderJson.get("description").asString

        return Note(orderId, noteId, title, description)
    }
}

class PriceTypeDeserializer() : JsonDeserializer<PriceType>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PriceType {
        val typeString = json!!.asString
        when(typeString){
            "PerItem" -> return PriceType.PerItem
            "PerMeter2" -> return PriceType.PerMeter2
            else -> return PriceType.PerItem
        }
    }
}

class PriceDeserializer() : JsonDeserializer<Price>{
    private fun priceTypeFromInt(priceType : Int) : PriceType {
        if(priceType == 0)
            return PriceType.PerItem
        else if(priceType == 1)
            return PriceType.PerMeter2
        else
            return PriceType.PerItem
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Price {
        val jsonObj = json!!.asJsonObject

        val priceValue = jsonObj.get("priceValue").asFloat
        val priceType = priceTypeFromInt(jsonObj.get("priceType").asInt)

        return Price(priceValue, priceType)
    }

}

class ProductDeserializer() : JsonDeserializer<Product>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Product {
        val jsonObj = json!!.asJsonObject

        val id = jsonObj.get("productId").asLong
        val name = jsonObj.get("name").asString
        val price = PriceDeserializer().deserialize(jsonObj.get("price"), null, null)
        val description = jsonObj.get("description").asString
        val category = null//ProductCategory(jsonObj.get("category").asString)
        val subCategory = null//ProductCategory(jsonObj.get("subCategory").asString)

        return Product(id, name, price, description, category, subCategory)
    }

}

class CategoryTreeDeserializer : JsonDeserializer<CategoryTree>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CategoryTree {
        val jsonArr = json!!.asJsonArray

        val categoriesMap =  hashMapOf<ProductCategory, List<ProductCategory>>()

        jsonArr.forEach{categoryJson ->
            val category = ProductCategory(categoryJson.asJsonObject.get("name").asString)
            val subcategories = categoryJson.asJsonObject.get("subcategories").asJsonArray.map { subcategoryJson ->
                ProductCategory(subcategoryJson.asString)
            }
            categoriesMap.put(category, subcategories)
        }

        return CategoryTree(categoriesMap)
    }

}