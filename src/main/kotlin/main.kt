fun main() {
    //Inicializamos el objeto
    val juego = Resources(10,10,7,7)

    println("Bienvenido al juego de hundir la flota\n")

    //Creamos el grid del oceano
    juego.crearOceano()

    //El jugador coloca sus barcos
    juego.barcosJugador()

    //El bot coloca sus barcos
    juego.barcosBot()

    //Se ejecuta la batalla hasta que gane alguien
    do{
        juego.batalla()
    } while (juego.playerShips != 0 && juego.computerShips != 0)

    //Fin del juego
    juego.gameOver()
}


