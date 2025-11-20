package com.example.mypasteleria

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.mypasteleria.Data.Model.Post
import com.example.mypasteleria.Repository.PostRepository
import com.example.mypasteleria.ViewModel.PostViewModel
import com.example.mypasteleria.ui.theme.Screens.PostScreen
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
class FakePostRepository(private val posts: List<Post>) : PostRepository() {
    override suspend fun getPosts(): List<Post> = posts
}
class PostScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()
    @Test
    fun el_titulo_de_post_debe_aparecer_en_pantalla() = runTest {

        val fakePosts = listOf(
            Post(1,1, "Titulo 1", "Cuerpo 1"),
            Post(2,2, "Titulo 2", "Cuerpo 2")
        )

        val dispatcher = StandardTestDispatcher(testScheduler)

        val fakeRepo = FakePostRepository(fakePosts)

        val viewModel = PostViewModel(fakeRepo, dispatcher)

        dispatcher.scheduler.advanceUntilIdle()
        composeRule.setContent {
            PostScreen(viewModel = viewModel)
        }
        composeRule.onNodeWithText("Titulo: Titulo 1").assertIsDisplayed()
        composeRule.onNodeWithText("Titulo: Titulo 2").assertIsDisplayed()
    }
}