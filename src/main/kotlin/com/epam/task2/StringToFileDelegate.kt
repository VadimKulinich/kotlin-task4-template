package com.epam.task2

import java.io.File
import kotlin.reflect.KProperty

/**
 * 1) Add operators to use the object of the class as  delegate using 'by' keyword
 * 2) Add in memory cache for get value. So second get will take value from memory not from cache
 * 3) After set value drop cache and write String to file
 * 4) For each get print additional message to console using 'println' method:
 *      4.1) For get from cache print value 'return from cache'
 *      4.2) For get from file print value 'return from file'
 * 5) For each set invocation print 'save to file' using 'println' method
 * 6) Each set value should override content of the file and not append text.
 *
 * Tips:
 * For reading and saving from file readText and writeText extensions might be used.
 */
class StringToFileDelegate(private val file: File)