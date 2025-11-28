import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.netology.Post
import ru.netology.WallServise

class WallServiceAddTest {

    lateinit var wallService: WallServise

    @Before
    public fun setUp() {
        wallService = WallServise
        WallServise.resetState()
    }

    @Test
    fun `после добавления поста id становится отличным от нуля`() {
        // Подготовленные данные
        val initialPost = Post(
            owerId = 12345,
            fromId = 12345,
            text = "Привет, друзья!",
            date = System.currentTimeMillis()
        )

        // Добавляем пост и проверяем результат
        val savedPost = wallService.add(initialPost)
        assertTrue("Идентификатор поста должен стать отличным от нуля после добавления", savedPost.id != 0)
    }
}