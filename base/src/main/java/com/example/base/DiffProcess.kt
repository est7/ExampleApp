package com.example.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import java.util.*
import kotlin.reflect.KProperty1

@Suppress("unused")
class DiffProcess<Model> private constructor() : DiffUtil.Callback() {

    private var identity: KProperty1<Model, *>? = null
    private var identityProperties: LinkedList<KProperty1<Model, *>>? = null
    private lateinit var contentProperties: Array<out KProperty1<Model, *>>

    companion object {
        fun <Model> KProperty1<Model, *>.isChanged(entry: Map.Entry<Any?, Any?>) =
            this == entry.key && true != entry.value

        @Suppress("MemberVisibilityCanBePrivate")
        infix fun <Model> KProperty1<Model, *>.and(another: KProperty1<Model, *>) =
            LinkedList<KProperty1<Model, *>>().apply {
                add(this@and)
                add(another)
            }

        @Suppress("MemberVisibilityCanBePrivate")
        infix fun <Model> LinkedList<KProperty1<Model, *>>.and(another: KProperty1<Model, *>) =
            LinkedList<KProperty1<Model, *>>().apply {
                addAll(this@and)
                add(another)
            }
    }

    constructor(
        identity: KProperty1<Model, *>,
        vararg contentProperties: KProperty1<Model, *>
    ) : this() {
        this.identity = identity
        this.contentProperties = contentProperties
    }

    constructor(
        identityProperties: LinkedList<KProperty1<Model, *>>,
        vararg contentProperties: KProperty1<Model, *>
    ) : this() {
        this.identityProperties = identityProperties
        this.contentProperties = contentProperties
    }

    private val oldDataList: MutableList<Model> = ArrayList()
    private val newDataList: MutableList<Model> = ArrayList()

    override fun getOldListSize() = oldDataList.size

    override fun getNewListSize() = newDataList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        identity?.let {
            val oldItemId = it.get(oldItemPosition.oldItem())
            val newItemId = it.get(newItemPosition.newItem())
            return oldItemId == newItemId
        }

        identityProperties?.let {
            it.forEach { prop ->
                val oldItemId = prop.get(oldItemPosition.oldItem())
                val newItemId = prop.get(newItemPosition.newItem())
                if (oldItemId != newItemId) return false
            }
            return true
        }

        return true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemPosition.oldItem()
        val newItem = newItemPosition.newItem()
        contentProperties.forEach {
            val oldItemProperty = it.get(oldItem)
            val newItemProperty = it.get(newItem)
            if (oldItemProperty notTheSame newItemProperty) {
                return false
            }
        }
        return true
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        val oldItem = oldItemPosition.oldItem()
        val newItem = newItemPosition.newItem()
        return contentProperties.map {
            val oldItemField = it.get(oldItem)
            val newItemField = it.get(newItem)
            it to (oldItemField theSame newItemField)
        }.toMap()
    }

    fun calculateDiff(
        oldDataList: MutableList<Model>,
        newDataList: List<Model>,
        detectMoves: Boolean
    ): DiffResult {
        this.oldDataList.clear()
        this.oldDataList.addAll(oldDataList)
        this.newDataList.clear()
        this.newDataList.addAll(newDataList)
        val result = DiffUtil.calculateDiff(this, detectMoves)
        oldDataList.clear()
        oldDataList.addAll(newDataList)
        return result
    }

    private fun Int.oldItem() = oldDataList[this]

    private fun Int.newItem() = newDataList[this]

    private infix fun <T> T?.notTheSame(another: T?) =
        !(this theSame another)

    private infix fun <T> T?.theSame(another: T?) = when {
        this == null && another == null -> true
        this == null || another == null -> false
        else -> this == another
    }
}