package com.example.hellotesting1

import org.junit.experimental.categories.Categories
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Categories::class)
@Categories.IncludeCategory(FastTests::class)
@Suite.SuiteClasses(CategoryUnitTest::class, Category2UnitTest::class)
class ExampleTestSuite {
}