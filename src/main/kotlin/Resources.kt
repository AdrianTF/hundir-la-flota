import java.util.*
import kotlin.random.Random
data class Resources(
    var numRows: Int,
    var numCols: Int,
    var playerShips: Int,
    var computerShips : Int

){

    var playerGrid: Array<Array<String?>> = Array(numRows) { arrayOfNulls<String>(numCols) }
    var computerGrid: Array<Array<String?>> = Array(numRows) { arrayOfNulls<String>(numCols) }
    val missedGuesses: Array<Array<Int?>> = Array(numRows) { arrayOfNulls<Int>(numCols) }

    fun crearOceano(){
        //Linea caracteres oceano
        print(" | ")
        for(i in 0..numCols-1){
            print("  $i   ")
        }
        println()

        //Primera seccion del oceano
        for (i in 0..playerGrid.size-1){
            for (j in 0..playerGrid[i].size-1){
                playerGrid[i][j] = "   ~  "
                computerGrid[i][j] = "   ~  "
                if(j==0){
                    print("$i|${playerGrid[i][j]}")
                }else if(j == playerGrid[i].size-1){
                    print("${playerGrid[i][j]}|$i")
                }else{
                    print("${playerGrid[i][j]}")
                }

            }
            println("")
        }

        //Segunda parte oceano
        print(" | ")
        for(i in 0..numCols-1){
            print("  $i   ")
        }
        println("")


    }

    fun barcosJugador(){

        println("Coloca tus barcos:")
        var i = 1;
        while (i<=playerShips){
            print("Inserte cordenada X para tu $i ship: ")
            var x = readln().toInt()
            print("Inserte cordenada Y para tu $i ship: ")
            var y = readln().toInt()

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (playerGrid[x][y].equals(" ~ "))){

                playerGrid[x][y] = " @ "
                i++

            }else if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && playerGrid[x][y].equals(" @ ")){
                println("No puedes poner 2 o mas barcos in la misma localizacion")
            }else if((x < 0 || x >= numRows) || (y < 0 || y >= numCols)){
                println("No puedes poner barcos fuera del oceano")
            }
            actualizarOceano()
        }

    }

    fun batalla(){
        turnoJugador()
        //turnoBot()

        actualizarOceano()
        println("\nTus barcos: $playerShips | Bot: $computerShips\n")
    }

    fun turnoJugador(){
        println("\nTU TURNO")
        var x :Int
        var y : Int

    }

    fun barcosOrdenador() {
        println("\nColocando barcos del ordenador.")

        var i = 1;
        while(i <= computerShips){
            var x = Random.nextInt(0, 10)
            var y = Random.nextInt(0, 10)

            if((x in 0 until numRows) && (y in 0 until numCols) && (playerGrid[x][y].equals(" ~ "))){
                computerGrid[x][y] = " x "
                println("$i. barco colocado.")
                i++
            }
        }
        actualizarOceano()
    }

    fun actualizarOceano(){
        println()

        print(" | ")
        for(i in 0..numCols-1){
            print("  $i   ")
        }
        println()

        for(x in 0..playerGrid.size){
            print("$x|")

            for(y in 0..playerGrid[x].size){
                print("${playerGrid[x][y]}")
            }

            println("|$x")
        }

        print(" | ")
        for(i in 0..numCols-1){
            print("  $i   ")
        }
        println("")

    }

    fun gameOver() {
        println("Your ships: $playerShips | Computer ships: $computerShips")
        if(playerShips > 0 && computerShips <= 0){
            println("¡Enhorabuena! Has ganado la batalla.")
        } else {
            println("Lo siento, has perdido la batalla.")
        }
        println()
    }
}
