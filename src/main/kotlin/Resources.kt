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


    }

    fun barcosOrdenador() {
        println("\nColocando barcos del ordenador.")

        var i = 1;
        while(i <= computerShips){
            var x = Random.nextInt(0, 10)
            var y = Random.nextInt(0, 10)

            if((x in 0 until numRows) && (y in 0 until numCols) && (playerGrid[x][y].equals(" ~ "))){
                computerGrid[x][y] = " x "
                println("$i. ship DEPLOYED")
                i++
            }
        }
        //actualizarOceano()
        //TODO uncomment method
    }
}
