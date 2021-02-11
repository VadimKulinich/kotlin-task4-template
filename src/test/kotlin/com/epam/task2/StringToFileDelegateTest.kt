package com.epam.task2

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

class StringToFileDelegateTest {

    private lateinit var outputStream: ByteArrayOutputStream
    private lateinit var file: File
    private val nextLine = System.lineSeparator()

    @Before
    fun setup() {
        file = File("src/test/resources/file.txt")
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        file.writeText("")
    }

    @After
    fun cleanup() {
        file.writeText("")
    }

    @Test
    fun read_from_empty_file_no_cache_check_string_value() {
        val delegatedString by StringToFileDelegate(file)
        assertEquals("", delegatedString)
    }

    @Test
    fun read_from_empty_file_no_cache_check_message() {
        val delegatedString by StringToFileDelegate(file)
        // imitate access to delegated value
        delegatedString.length
        assertEquals("return from file$nextLine", String(outputStream.toByteArray()))
    }

    @Test
    fun read_from_empty_file_cached_check_message() {
        val delegatedString by StringToFileDelegate(file)
        // imitate access to delegated value
        delegatedString.length
        // clear buffer
        outputStream.reset()
        // imitate second access
        delegatedString.length
        assertEquals("return from cache$nextLine", String(outputStream.toByteArray()))
    }

    @Test
    fun read_from_not_empty_file() {
        val text = "some text"
        file.writeText(text)
        val delegatedString by StringToFileDelegate(file)
        // imitate access to delegated value
        delegatedString.length
        assertEquals(text, delegatedString)
    }

    @Test
    fun read_from_empty_file_cache_check_string_value() {
        val delegatedString by StringToFileDelegate(file)
        // imitate access to delegated value
        delegatedString.length
        // imitate second access to delegated value
        delegatedString.length
        assertEquals("", delegatedString)
    }

    @Test
    fun write_to_file_check_file_content() {
        val text = "some value"
        var delegatedString by StringToFileDelegate(file)
        // imitate set
        delegatedString = text
        assertEquals(text, file.readText())
    }

    @Test
    fun write_to_file_check_message() {
        val text = "some value"
        var delegatedString by StringToFileDelegate(file)
        // imitate set
        delegatedString = text
        assertEquals("save to file$nextLine", String(outputStream.toByteArray()))
    }

    @Test
    fun write_to_file_check_cache_dropped() {
        val text = "some value"
        var delegatedString by StringToFileDelegate(file)
        // imitate get cache added
        delegatedString.length
        // imitate set drop cache
        delegatedString = text
        // imitate get read from file init cache
        delegatedString.length
        assertEquals(
                "return from file${nextLine}save to file${nextLine}return from file$nextLine",
                String(outputStream.toByteArray())
        )
    }
}