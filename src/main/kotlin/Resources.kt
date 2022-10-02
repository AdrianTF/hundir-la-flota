import kotlin.random.Random

data class Resources(
    var numRows: Int,
    var numCols: Int,
    var playerShips: Int,
    var computerShips: Int

) {

    var playerGrid: Array<Array<String?>> = Array(numRows) { arrayOfNulls<String>(numCols) }
    var computerGrid: Array<Array<String?>> = Array(numRows) { arrayOfNulls<String>(numCols) }
    val missedGuesses: Array<Array<Int?>> = Array(numRows) { arrayOfNulls<Int>(numCols) }
    //TODO Revisar todos los strings y asegurarnos de que sean iguales, sino las condiciones no se cumpliran nunca.
    fun crearOceano() {
        //Linea caracteres oceano
        print(" | ")
        for (i in 0..numCols - 1) {
            print("  $i   ")
        }
        println()

        //Primera seccion del oceano
        for (i in 0..playerGrid.size - 1) {
            for (j in 0..playerGrid[i].size - 1) {
                playerGrid[i][j] = "   ~  "
                computerGrid[i][j] = "   ~  "
                if (j == 0) {
                    print("$i|${playerGrid[i][j]}")
                } else if (j == playerGrid[i].size - 1) {
                    print("${playerGrid[i][j]}|$i")
                } else {
                    print("${playerGrid[i][j]}")
                }

            }
            println("")
        }

        //Segunda parte oceano
        print(" | ")
        for (i in 0..numCols - 1) {
            print("  $i   ")
        }
        println("")


    }

    fun barcosJugador() {

        println("Coloca tus barcos:")
        var i = 1;
        while (i <= playerShips) {
            print("Inserte cordenada X para tu barco #$i: ")
            var x = readln().toInt()
            print("Inserte cordenada Y para tu barco #$i: ")
            var y = readln().toInt()

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (playerGrid[x][y].equals("   ~  "))) {

                playerGrid[x][y] = "   @  "
                i++

            } else if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && playerGrid[x][y].equals("   @  ")) {
                println("No puedes poner 2 o mas barcos in la misma localizacion")
            } else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols)) {
                println("No puedes poner barcos fuera del oceano")
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

    fun turnoJugador() {
        println("\nTU TURNO")
        var x: Int
        var y: Int

        do {
            print("Inserta coordenada x: ")
            x = readln().toInt()
            print("Inserta coordenada y: ")
            y = readln().toInt()
            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (computerGrid[x][y].equals("  x  ")) {
                    println("POW! Has hundido un barco");
                    playerGrid[x][y] = " ! ";
                    --computerShips;
                } else if (playerGrid[x][y].equals("  @  ")) {
                    println("Oh no, has hundido tu propio barco :(");
                    playerGrid[x][y] = " ! ";
                    --playerShips;
                } else if (playerGrid[x][y].equals("   ~  ")) {
                    println("Lo siento, has fallado");
                    playerGrid[x][y] = "   -  ";
                }
            } else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
                println("No puedes disparar a barcos fuera del oceano");


        } while ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))

    }

    fun turnoBot() {
        println("\nTURNO BOT")

        var x = -1
        var y = -1
        do {
            var x = Random.nextInt(0, 10)
            var y = Random.nextInt(0, 10)
            if (x >= 0 && x < numRows && y >= 0 && y < numCols) {
                if (playerGrid[x][y].equals("   @  ")) {
                    println("El bot ha hundido uno de tus barcos!")
                    playerGrid[x][y] = "   x  "
                    --playerShips
                    ++computerShips
                } else if (computerGrid[x][y].equals("   x  ")) {
                    println("El bot ha hundido su propio barco")
                    playerGrid[x][y] = "   !  "
                } else if (playerGrid[x][y].equals("   ~  ")) {
                    println("El ordenador ha fallado")

                    if (missedGuesses[x][y] !== 1) missedGuesses[x][y] = 1
                }
            }
        } while (x < 0 || x >= numRows || y < 0 || y >= numCols)

    }

    fun barcosOrdenador() {
        println("\nColocando barcos del ordenador.")

        var i = 1;
        while (i <= computerShips) {
            var x = Random.nextInt(0, 10)
            var y = Random.nextInt(0, 10)

            if ((x in 0 until numRows) && (y in 0 until numCols) && (playerGrid[x][y].equals("   ~  "))) {
                computerGrid[x][y] = "   x  "
                println("$i. barco colocado.")
                i++
            }
        }
        actualizarOceano()
    }

    fun actualizarOceano() {
        println()

        print(" | ")
        for (i in 0..numCols - 1) {
            print("  $i   ")
        }
        println()

        for (x in 0 until playerGrid.size) { //Aádido until para que no de error de bound
            print("$x|")

            for (y in 0 until playerGrid[x].size) { //Aádido until para que no de error de bound
                print("${playerGrid[x][y]}")
            }

            println("|$x")
        }

        print(" | ")
        for (i in 0..numCols - 1) {
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
