package com.nicolascristaldo.foodrecipes.domain

import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetFilterAttributesUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: FoodRecipeRepository

    private lateinit var getFilterAttributesUseCase: GetFilterAttributesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getFilterAttributesUseCase = GetFilterAttributesUseCase(repository)
    }

    @Test
    fun `get areas returns correctly filtered data`() = runBlocking {
        //Given
        coEvery { repository.getAreas() } returns listOf("Area1", "Unknown")

        //When
        val result = getFilterAttributesUseCase()

        //Then
        assert(result.areas == listOf("Area1"))
    }
}