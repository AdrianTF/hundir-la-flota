fun main() {
    //Inicializamos el objeto
    val juego = Resources(10,10,7,7)

    println("Bienvenido al juego de hundir la flota")
    println("  ")


    juego.crearOceano()

    juego.barcosJugador()

    juego.barcosBot()

    do{
        juego.batalla()
    } while (juego.playerShips != 0 && juego.computerShips != 0)

    juego.gameOver()
}


