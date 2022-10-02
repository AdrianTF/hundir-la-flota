import com.sun.xml.internal.fastinfoset.util.StringArray


fun main() {

    var rs = Resources(10,10,7,7)

    println("Bienvenido al juego de hundir la flota")
    println("  ")


    rs.crearOceano()

    rs.barcosJugador()

    rs.barcosOrdenador()

    do{
        rs.batalla()
    } while (rs.playerShips != 0 && rs.computerShips != 0)

    rs.gameOver()
}


