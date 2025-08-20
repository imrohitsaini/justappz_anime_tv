package com.justappz.aniyomitv.utils

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken

object GsonUtils {

    val gson: Gson by lazy { Gson() }

    // Convert object to JSON string
    fun toJson(any: Any): String = gson.toJson(any)

    // Convert JSON string to object of type T
    inline fun <reified T> toObject(json: String): T {
        return gson.fromJson(json, object : TypeToken<T>() {}.type)
    }

    // Convert List<T> to JsonArray
    fun <T> listToJsonArray(list: List<T>): JsonArray {
        return gson.toJsonTree(list).asJsonArray
    }
}

// Extension functions

// Any object to JSON string
fun Any.toJson(): String = GsonUtils.toJson(this)

// JSON string to object
inline fun <reified T> String.toObject(): T = GsonUtils.toObject(this)

// List<T> to JsonArray
fun <T> List<T>.toJsonArray(): JsonArray = GsonUtils.listToJsonArray(this)