import kotlin.random.Random

data class Resources(
    var numRows: Int,
    var numCols: Int,
    var playerShips: Int,
    var computerShips: Int
) {
    private var playerGrid: Array<Array<String?>> = Array(numRows) { arrayOfNulls(numCols) }
    private var computerGrid: Array<Array<String?>> = Array(numRows) { arrayOfNulls(numCols) }

    fun crearOceano() {
        //Linea caracteres oceano
        print(" | ")
        for (i in 0 until numCols) {
            print("  $i   ")
        }
        println()

        //Primera seccion del oceano
        for (i in playerGrid.indices) {
            for (j in 0 until playerGrid[i].size) {
                playerGrid[i][j] = "   ~  "
                computerGrid[i][j] = "   ~  "
                when (j) {
                    0 -> {
                        print("$i|${playerGrid[i][j]}")
                    }

                    playerGrid[i].size - 1 -> {
                        print("${playerGrid[i][j]}|$i")
                    }

                    else -> {
                        print("${playerGrid[i][j]}")
                    }
                }
            }
            println("")
        }

        //Segunda parte oceano
        print(" | ")
        for (i in 0 until numCols) {
            print("  $i   ")
        }
        println("")
    }

    fun barcosJugador() {
        println("Coloca tus barcos:")
        var i = 1
        while (i <= playerShips) {
            var x: Int
            var y: Int
            do {
                print("Inserta coordenada x para tu barco #$i: ")
                x = try {
                    readln().toInt()
                } catch (e: NumberFormatException) {
                    99
                }
                print("Inserta coordenada y para tu barco #$i: ")
                y = try {
                    readln().toInt()
                } catch (e: NumberFormatException) {
                    99
                }
                if (x == 99 || y == 99) {
                    println("No ha escrito en un formato correcto las coordenadas vuelva a escribirlas.")
                }
            } while (x == 99 || y == 99)

            if ((x in 0 until numRows) && (y in 0 until numCols) && (playerGrid[x][y].equals("   ~  "))) {
                playerGrid[x][y] = "   @  "
                i++
            } else if ((x in 0 until numRows) && (y in 0 until numCols) && playerGrid[x][y].equals("   @  ")) {
                println("No puedes poner 2 o mas barcos en la misma localización.")
            } else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols)) {
                println("No puedes poner barcos fuera del oceano.")
            }
            actualizarOceano()
        }
    }

    fun batalla() {
        turnoJugador()
        turnoBot()
        actualizarOceano()
        println("\nTus barcos: $playerShips | Bot: $computerShips\n")
    }

    private fun turnoJugador() {
        println("\nTU TURNO")
        var x: Int
        var y: Int
        do {
            do {
                print("Inserta coordenada x: ")
                x = try {
                    readln().toInt()
                } catch (e: NumberFormatException) {
                    99
                }
                print("Inserta coordenada y: ")
                y = try {
                    readln().toInt()
                } catch (e: NumberFormatException) {
                    99
                }
                if (x == 99 || y == 99) {
                    println("No ha escrito en un formato correcto las coordenadas vuelva a escribirlas.")
                }
            } while (x == 99 || y == 99)

            if ((x in 0 until numRows) && (y in 0 until numCols)) //valid guess
            {
                if (computerGrid[x][y].equals("   @  ")) {
                    println("¡PUM! Has hundido un barco")
                    playerGrid[x][y] = "   #  "
                    computerGrid[x][y] = "   #  "
                    --computerShips
                } else if (playerGrid[x][y].equals("   @  ")) {
                    println("¡Oh no, has hundido tu propio barco! :(")
                    playerGrid[x][y] = "   !  "
                    --playerShips
                } else if (playerGrid[x][y].equals("   ~  ") || playerGrid[x][y].equals("   -  ")) {
                    println("Lo siento, has fallado...")
                    playerGrid[x][y] = "   -  "
                }
            } else {
                println("No puedes disparar a barcos fuera del oceano.")
            }
        } while ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
    }

    private fun turnoBot() {
        println("\nTURNO BOT")
        do {
            val x = Random.nextInt(0, 10)
            val y = Random.nextInt(0, 10)
            if (x in 0 until numRows && y >= 0 && y < numCols) {
                if (playerGrid[x][y].equals("   @  ")) {
                    println("¡El bot ha hundido uno de tus barcos!")
                    playerGrid[x][y] = "   !  "
                    --playerShips
                } else if (computerGrid[x][y].equals("   @  ")) {
                    println("El bot ha hundido su propio barco...")
                    playerGrid[x][y] = "   #  "
                    computerGrid[x][y] = "   #  "
                    --computerShips
                } else if (playerGrid[x][y].equals("   ~  ") || playerGrid[x][y].equals("   -  ")) {
                    println("El bot ha fallado :)")
                }
            }
        } while (x < 0 || x >= numRows || y < 0 || y >= numCols)

    }

    fun barcosBot() {
        println("\nColocando barcos del bot.")
        var i = 1
        while (i <= computerShips) {
            val x = Random.nextInt(0, 10)
            val y = Random.nextInt(0, 10)
            if ((x in 0 until numRows) && (y in 0 until numCols) && (playerGrid[x][y].equals("   ~  "))) {
                computerGrid[x][y] = "   @  "
                println("$i. barco colocado.")
                i++
            }
        }
        actualizarOceano()
    }

    private fun actualizarOceano() {
        println()

        print(" | ")
        for (i in 0 until numCols) {
            print("  $i   ")
        }
        println()

        for (x in playerGrid.indices) { //Aádido until para que no de error de bound
            print("$x|")
            for (y in 0 until playerGrid[x].size) { //Aádido until para que no de error de bound
                print("${playerGrid[x][y]}")
            }
            println("|$x")
        }

        print(" | ")
        for (i in 0 until numCols) {
            print("  $i   ")
        }
        println("")
    }

    fun gameOver() {
        println("Tus barcos: $playerShips | Bot: $computerShips")
        if (playerShips > 0 && computerShips <= 0) {
            println("¡Enhorabuena! Has ganado la batalla.")
        } else {
            println("Lo siento, has perdido la batalla.")
        }
        println()
    }
}
