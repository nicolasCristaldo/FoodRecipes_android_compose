package com.nicolascristaldo.foodrecipes.domain

import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import com.nicolascristaldo.foodrecipes.domain.model.preview.toPreview
import com.nicolascristaldo.foodrecipes.domain.model.recipe.RecipeList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetRecipesByCriteriaUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: FoodRecipeRepository

    private lateinit var getRecipesByCriteriaUseCase: GetRecipesByCriteriaUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getRecipesByCriteriaUseCase = GetRecipesByCriteriaUseCase(repository)
    }

    @Test
    fun `when all parameters are null, return empty list`() = runBlocking {
        //When
        val result = getRecipesByCriteriaUseCase(name = null, area = null, category = null)

        //Then
        assert(result.recipes.isNullOrEmpty())
    }

    @Test
    fun `when name is not null, return recipe preview list`() = runBlocking {
        //Given
        val recipeList = RecipeList(emptyList())
        coEvery { repository.getRecipesByName("") } returns recipeList

        //When
        val result = getRecipesByCriteriaUseCase(name = "", area = null, category = null)

        //Then
        assertTrue(result == recipeList.toPreview())
    }
}