package ru.netology

data class Post(
    val id: Int = 0, // уникальный идентификатор
    val owerId: Int, // владелец стены
    val fromId: Int, // отрпавитель поста
    val text: String = "", // текст поста
    val friendsOnly: Boolean = false,// только для друзей
    val copiraght: String = "", // источник материала
    val date: Long = 2,
    val comment: Comments = Comments(),
    val likes: Likes = Likes()
) {
    override fun toString(): String {
        return "Post(идентификатор=${id}, text='${text}', ${comment})"

    }
}
data class Comments(
    val count: Int = 0,// количество
    val canPost: Boolean = true, // модет ли пользователь комментировать
    val groopCanPost: Boolean = true // могут ли группы комментировать пост
) {
    override fun toString(): String {
        return "количество комментарием ${count}"
    }
}

data class Likes(
    val count: Int = 0, // количество лайков
    val userLike: Boolean = true, // наличие лайка от текущего пользователя
    val canLike: Boolean = true // информация о том может ли текущий пользователь поставить лайк
)

object WallServise {
    private val posts = mutableListOf<Post>()  //создали массив куда будем помещать все посты

    // создаем переменную куда будем класть уникальный идентификатор
    var idNull = 0

    fun add(post: Post): Post {
        val uniqId = ++idNull
        val newPost = post.copy(id = uniqId)
        posts.add(newPost)
        return newPost


    }
    fun update (post: Post) : Boolean {
        // Проходим цикл по всему списку постов
        // Проверяем, совпадает ли идентификатор текущего поста с искомым
        // Если совпадение найдено, обновляем пост и возвращаем true
        for ((index,currentPost) in posts.withIndex()) {
            if (currentPost.id == post.id) {
                posts[index] = post
                return  true
            }

        }
        return false
    }

}

fun main() {


    val firstPost = Post(  // создали пост
        owerId = 12345,
        fromId = 1233,
        text = "Привет",
        comment = Comments(1)

    )
    val SecondPost = Post(  // создали пост
        owerId = 12345,
        fromId = 1233,
        text = "Пока"
    )

    val newPost = WallServise.add(firstPost)
    val modifPost = newPost.copy(text = "Привет мир")
    val savedPost = WallServise.update(modifPost)
    println("$savedPost")

    // Добавляем пост и получаем его с уникальным идентификатором
    // Модифицируем текст поста
    // Пробуем обновить пост

}