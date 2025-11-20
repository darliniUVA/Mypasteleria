package com.example.mypasteleria

import com.example.mypasteleria.Data.Model.Post
import com.example.mypasteleria.Data.Remote.ApiService
import com.example.mypasteleria.ViewModel.PostViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModelTest : StringSpec({
    "postList se actualiza correctamente tras fetchPosts()" {
        val fakePosts = listOf(
            Post(1, 1, "Titulo 1", "Contenido 1"),
            Post(2, 2, "Titulo 2", "Contenido 2")
        )

        val mockApi = mockk<ApiService>()
        coEvery { mockApi.getPosts() } returns fakePosts

        val testRepo = TestTablePostRepository(mockApi)

        val dispatcher = StandardTestDispatcher()

        val viewModel = PostViewModel(testRepo, dispatcher)
        runTest(dispatcher) {
            viewModel.fetchPosts()
            advanceUntilIdle()
            viewModel.postList.value shouldContainExactly fakePosts
        }
    }
})