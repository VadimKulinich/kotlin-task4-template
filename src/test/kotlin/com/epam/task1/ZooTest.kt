package com.epam.task1

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ZooTest {

    private lateinit var outputStream: ByteArrayOutputStream
    private val nextLine = System.lineSeparator()

    @Before
    fun setup() {
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_zoo_negative_animals_limit() {
        Zoo.create(-1, 10.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_zoo_zero_animals_limit() {
        Zoo.create(0, 10.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_zoo_negative_weight_limit() {
        Zoo.create(10, -10.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_zoo_zero_weight_limit() {
        Zoo.create(10, -10.0)
    }

    @Test
    fun check_zoo_size_no_actions() {
        val zoo = Zoo.create(1, 10.0)
        assertEquals(0, zoo.getAllAnimals().size)
    }

    @Test
    fun add_animal_to_zoo_check_size() {
        val zoo = Zoo.create(1, 10.0)
        val animal = Animal("some name", 10.0)
        zoo += animal
        assertEquals(1, zoo.getAllAnimals().size)
    }

    @Test
    fun add_animal_to_zoo_check_zoo_content() {
        val zoo = Zoo.create(1, 10.0)
        val animal = Animal("some name", 10.0)
        zoo += animal
        assertEquals(
                "Hashcode and equals not implemented",
                Animal("some name", 10.0),
                zoo.getAllAnimals().firstOrNull()
        )
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun add_animal_to_zoo_negative_weight() {
        val zoo = Zoo.create(1, 10.0)
        val animal = Animal("some name", -10.0)
        zoo += animal
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun add_animal_to_zoo_animals_limit_exceed() {
        val zoo = Zoo.create(1, 50.0)
        val animal1 = Animal("some name", 10.0)
        val animal2 = Animal("some name2", 10.0)
        zoo += animal1
        zoo += animal2
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun add_animal_to_zoo_weight_limit_exceed() {
        val zoo = Zoo.create(5, 10.0)
        val animal1 = Animal("some name", 10.0)
        val animal2 = Animal("some name2", 10.0)
        zoo += animal1
        zoo += animal2
    }

    @Test
    fun add_animal_to_zoo_weight_equal_to_limit() {
        val zoo = Zoo.create(5, 10.0)
        val animal1 = Animal("some name", 10.0)
        zoo += animal1
        assertEquals(1, zoo.getAllAnimals().size)
    }

    @Test
    fun add_animal_to_zoo_animals_count_equal_to_limit() {
        val zoo = Zoo.create(1, 20.0)
        val animal1 = Animal("some name", 10.0)
        zoo += animal1
        assertEquals(1, zoo.getAllAnimals().size)
    }

    @Test
    fun remove_animal_from_zoo() {
        val zoo = Zoo.create(1, 10.0)
        val animal = Animal("some name", 10.0)
        zoo += animal
        zoo -= animal
        assertEquals(0, zoo.getAllAnimals().size)
    }

    @Test
    fun double_add_animal_from_zoo() {
        val zoo = Zoo.create(5, 100.0)
        val animal1 = Animal("some name", 10.0)
        val animal2 = Animal("some name2", 20.0)
        zoo += animal1
        zoo += animal2
        assertEquals(2, zoo.getAllAnimals().size)
    }

    @Test
    fun double_remove_animal_from_zoo() {
        val zoo = Zoo.create(2, 100.0)
        val animal1 = Animal("some name", 10.0)
        val animal2 = Animal("some name2", 20.0)
        zoo += animal1
        zoo += animal2

        zoo -= animal1
        assertEquals(1, zoo.getAllAnimals().size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun remove_from_zoo_by_index() {
        val zoo = Zoo.create(1, 10.0)
        val animal = Animal("some name", 10.0)
        zoo += animal
        zoo -= 1
    }

    @Test(expected = IllegalArgumentException::class)
    fun remove_from_zoo_by_index_empty_zoo() {
        val zoo = Zoo.create(1, 10.0)
        zoo -= 0
    }

    @Test(expected = IllegalArgumentException::class)
    fun remove_from_zoo_by_negative_index() {
        val zoo = Zoo.create(-2, 10.0)
        val animal = Animal("some name", 10.0)
        zoo += animal
        zoo -= -1
    }

    @Test
    fun invoke_zoo_no_results() {
        val zoo = Zoo.create(1, 10.0)
        zoo.invoke()
        val output = String(outputStream.toByteArray())
        assertEquals(output, "")
    }

    @Test
    fun invoke_zoo_single_results() {
        val zoo = Zoo.create(1, 10.0)
        val animal = Animal("some name", 10.0)
        zoo += animal
        zoo.invoke()
        val output = String(outputStream.toByteArray())
        val expected = "${animal.name} fed completed$nextLine"
        assertEquals(output, expected)
    }

    @Test
    fun invoke_zoo_double_results() {
        val zoo = Zoo.create(2, 100.0)
        val animal1 = Animal("some name1", 10.0)
        val animal2 = Animal("some name2", 10.0)
        zoo += animal1
        zoo += animal2
        zoo.invoke()
        val output = String(outputStream.toByteArray())
        val expected = "${animal1.name} fed completed$nextLine${animal2.name} fed completed$nextLine"
        assertEquals(output, expected)
    }

    @Test
    fun invoke_zoo_check_by_weight() {
        val zoo = Zoo.create(2, 100.0)
        val animal1 = Animal("some name1", 10.0)
        val animal2 = Animal("some name2", 20.0)
        zoo += animal1
        zoo += animal2
        zoo.invoke(5.0)
        val output = String(outputStream.toByteArray())
        val expected = "${animal1.name}$nextLine${animal2.name}$nextLine"
        assertEquals(output, expected)
    }

    @Test
    fun invoke_zoo_check_by_weight_no_results() {
        val zoo = Zoo.create(2, 100.0)
        val animal1 = Animal("some name1", 10.0)
        val animal2 = Animal("some name2", 20.0)
        zoo += animal1
        zoo += animal2
        zoo.invoke(50.0)
        val output = String(outputStream.toByteArray())
        assertEquals(output, "")
    }
}