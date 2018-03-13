package com.dgtek.jspring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import javax.persistence.*


/////////////// Blog example ////////////////
@Entity
@Table(name = "BlogUsers")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val uid: Long = 0,
        val name: String = "",
        val nickName: String = "")

/**        ,
@OneToMany (fetch=FetchType.LAZY, mappedBy = "uid") @JoinColumn(name="uid") var blogs: List<Blog>)

@Entity @Table(name="Blogs")
data class Blog (
@Id @GeneratedValue(strategy=GenerationType.AUTO) val blogId: Long=0,
@ManyToOne @JoinColumn(name="uid")  var uid: Long,
val name: String = "",
val nickName: String = "" )
)
 */
@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByName(name: String): List<User>
    fun findByNickName(nickName: String): List<User>

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.nickName = :nickName")
    fun findByNameAndNickName(name: String, nickName: String): List<User>
}


interface Service {
    fun getMessage(): String
}

data class Greeting(val id: Long, val content: String)

@Component
class MsgPrinter(@Autowired val service: Service) {
    fun print() = print(service.getMessage())
}

@Component
class ServiceImpl : Service {
    override fun getMessage() = "Kotlin rocks !!"
}

