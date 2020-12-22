package com.epam.collections

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class TestArrayList<V: Any>() : MutableList<V?> {

    override val size: Int = 0

    override fun contains(element: V?): Boolean = false

    override fun containsAll(elements: Collection<V?>): Boolean = false

    override fun isEmpty(): Boolean = false

    override fun add(element: V?): Boolean = false

    override fun addAll(elements: Collection<V?>): Boolean = false

    override fun clear() {

    }

    override fun iterator(): MutableIterator<V?> {
        TODO("Not yet implemented")
    }

    override fun remove(element: V?): Boolean = false

    override fun removeAll(elements: Collection<V?>): Boolean = false

    override fun retainAll(elements: Collection<V?>): Boolean = false

    override fun get(index: Int): V? = null

    override fun indexOf(element: V?): Int = 0

    override fun lastIndexOf(element: V?): Int = 0

    override fun add(index: Int, element: V?) {

    }

    override fun addAll(index: Int, elements: Collection<V?>): Boolean = false

    override fun listIterator(): MutableListIterator<V?> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): MutableListIterator<V?> {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int): V? = null

    override fun set(index: Int, element: V?): V? {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<V?> {
        TODO("Not yet implemented")
    }
}