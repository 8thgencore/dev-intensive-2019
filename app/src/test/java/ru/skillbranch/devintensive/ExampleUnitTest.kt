package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user1 = User("1")
        val user2 = User("2", "John", "Wick")
        print("$user1")
    }

    @Test
    fun test_factory() {
        val user = User.makeUser("John Wick")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")

        fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(lastVisit = Date().add(2, TimeUnits.SECOND))

        println(
            """
            ${user.lastVisit}
            ${user2.lastVisit?.format()}
        """.trimIndent()
        )
    }

    @Test
    fun test_data_maping() {
        val user = User.makeUser("Махеев Михаил")
        val newUser = user.copy(lastVisit = Date().add(-500, TimeUnits.SECOND))
        val userView = newUser.toUserView()

        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Махеев Михаил")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image", type = "image")

        when (txtMessage) {
            is TextMessage -> println("this is text message")
            is ImageMessage -> println("this is image message")
        }
    }
}