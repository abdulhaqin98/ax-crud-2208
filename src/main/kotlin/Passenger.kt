import java.sql.DriverManager

// create a model class
data class User(val pid: Int, val pname: String, val page: String, val pphone: String)

fun main(args: Array<String>) {
    val jdbcUrl = "jdbc:mysql://localhost:3306/axisotb"
    // connect
    val connection = DriverManager.getConnection(jdbcUrl, "root", "password")

    // check connection
    println(connection.isValid(0))

    //insert
    val res = connection.createStatement().executeUpdate("insert into passenger(pname, page, pgender, pphone) values('Ray', 24, 'M', '9887878765')")
    if(res > 0) {
        println("successfully inserted record into passengers db !!!")
    } else {
        println("Insert Not sucessfut")
    }

    // update
    val update_res = connection.createStatement().executeUpdate("update passenger set pname = 'Rim' where pid = 3")
    if( update_res > 0) {
        println("successfully updated record in passengers db !!!")
    } else {
        println("Update Not sucessful")
    }

    // delete
    val delete_res = connection.createStatement().executeUpdate("delete from passenger where pid = 6")
    if( delete_res > 0) {
        println("successfully deleted record in passengers db !!!")
    } else {
        println("Delete Not sucessful")
    }

    // select

    val query = connection.prepareStatement("SELECT * FROM passenger")
    val result = query.executeQuery()
    val users = mutableListOf<User>()

    while(result.next()){

        val pid = result.getInt("pid")
        val pname = result.getString("pname")

        val page = result.getString("page")
        val pphone = result.getString("pphone")

        users.add(User(pid, pname, page, pphone))
    }
    println(users)
}