package com.epam

import com.epam.task1.ZooTest
import com.epam.task2.StringToFileDelegateTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(ZooTest::class, StringToFileDelegateTest::class)
class SuiteTest