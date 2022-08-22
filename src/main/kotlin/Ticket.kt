import java.sql.DriverManager

// create a model class
data class Ticket(val ticket_id: Int, val ticket_number: Int, val pid: Int, val tid: Int, val ticket_price: Int)

fun main(args: Array<String>) {
    val jdbcUrl = "jdbc:mysql://localhost:3306/axisotb"
    // connect
    val connection = DriverManager.getConnection(jdbcUrl, "root", "password")

    // check connection
    println(connection.isValid(0))

    //insert
    val res = connection.createStatement().executeUpdate("insert into ticket(ticket_number, pid, tid, ticket_price) values(1234, 9, 5, 788)")
    if(res > 0) {
        println("successfully inserted record into ticket db !!!")
    } else {
        println("Insert Not sucessfut")
    }

    // update
    val update_res = connection.createStatement().executeUpdate("update ticket set ticket_price = 200 where tid = 2")
    if( update_res > 0) {
        println("successfully updated record in ticket db!")
    } else {
        println("Update Not sucessful")
    }

    // delete
    val delete_res = connection.createStatement().executeUpdate("delete from ticket where tid = 1")
    if( delete_res > 0) {
        println("successfully deleted record in ticket db!")
    } else {
        println("Delete Not sucessful")
    }

    // select

    val query = connection.prepareStatement("SELECT * FROM ticket")
    val result = query.executeQuery()
    val users = mutableListOf<Ticket>()

    while(result.next()){

        val ticket_id = result.getInt("ticket_id")
        val ticket_number = result.getInt("ticket_number")

        val pid = result.getInt("pid")
        val tid = result.getInt("tid")

        val ticket_price = result.getInt("ticket_price")

        users.add(Ticket(ticket_id, ticket_number, pid, tid, ticket_price))
    }
    println(users)
}