import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.netology.Post
import ru.netology.WallServise

class WallServiceUpdateTest {

    lateinit var wallService: WallServise

    @Before
    fun setUp() {
        wallService = WallServise
    }

    @Test
    fun `метод update возвращает true, если пост существует и обновляется`() {
        // Создаем новый пост и добавляем его
        val initialPost = Post(
            owerId = 12345,
            fromId = 12345,
            text = "Привет, друзья!",
            date = System.currentTimeMillis()
        )
        val savedPost = wallService.add(initialPost)

        // Изменяем текст поста
        val modifiedPost = savedPost.copy(text = "Привет, коллеги!")

        // Актуализируем пост и проверяем результат
        val success = wallService.update(modifiedPost)
        assertTrue("Обновление существующего поста должно вернуть true", success)
    }

    @Test
    fun `метод update возвращает false, если пост не существует`() {
        // Создаем пост с несуществующим идентификатором
        val nonExistingPost = Post(
            id = 99999, // намеренно используем несуществующий id
            owerId = 12345,
            fromId = 12345,
            text = "Привет, незнакомец!",
            date = System.currentTimeMillis()
        )

        // Пробуем обновить несуществующий пост
        val success = wallService.update(nonExistingPost)
        assertFalse("Обновление несуществующего поста должно вернуть false", success)
    }
}