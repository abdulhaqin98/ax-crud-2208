import java.sql.DriverManager

// create a model class
data class Trains(val tid: Int, val tno: String, val tname: String, val tsource: String, val tdestination: String, val tstart: String, val tarrive: String)

fun main(args: Array<String>) {
    val jdbcUrl = "jdbc:mysql://localhost:3306/axisotb"
    // connect
    val connection = DriverManager.getConnection(jdbcUrl, "root", "password")

    // check connection
    println(connection.isValid(0))

    //insert
    val res = connection.createStatement().executeUpdate("insert into train(tno, tname, tsource, tdestination, tstart, tarrive) values('200001','RC Express', 'pune', 'delhi', '2021/09/04', '2021/09/05')")
    if(res > 0) {
        println("successfully inserted record into train db !!!")
    } else {
        println("Insert Not sucessfut")
    }

    // update
    val update_res = connection.createStatement().executeUpdate("update train set tname = 'Rim Express' where tid = 3")
    if( update_res > 0) {
        println("successfully updated record in train db!")
    } else {
        println("Update Not sucessful")
    }

    // delete
    val delete_res = connection.createStatement().executeUpdate("delete from train where tid = 2")
    if( delete_res > 0) {
        println("successfully deleted record in train db!")
    } else {
        println("Delete Not sucessful")
    }

    // select

    val query = connection.prepareStatement("SELECT * FROM train")
    val result = query.executeQuery()
    val users = mutableListOf<Trains>()

    while(result.next()){

        val tid = result.getInt("tid")
        val tno = result.getString("tno")

        val tname = result.getString("tname")
        val tsource = result.getString("tsource")
        val tdestination = result.getString("tdestination")

        val tstart = result.getString("tstart")
        val tarrive = result.getString("tarrive")

        users.add(Trains(tid, tno, tname, tsource, tdestination, tstart, tarrive))
    }
    println(users)
}