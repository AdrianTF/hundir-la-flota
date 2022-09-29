import com.sun.xml.internal.fastinfoset.util.StringArray

fun main() {

    var rs = Resources(10,10,7,7)

    println("Bienvenido al juego de hundir la flota")
    println("  ")

    //Linea caracteres oceano
    print(" | ")
    for(i in 0..rs.numCols-1){
        print("  $i   ")
    }
    println()

    //Primera seccion del oceano
    for (i in 0..rs.playerGrid.size-1){
        for (j in 0..rs.playerGrid[i].size-1){
            rs.playerGrid[i][j] = "   ~  "
            rs.computerGrid[i][j] = "   ~  "
            if(j==0){
                print("$i|${rs.playerGrid[i][j]}")
            }else if(j == rs.playerGrid[i].size-1){
                print("${rs.playerGrid[i][j]}|$i")
            }else{
                print("${rs.playerGrid[i][j]}")
            }

        }
        println("")
    }

    //Segunda parte oceano
    print(" | ")
    for(i in 0..rs.numCols-1){
        print("  $i   ")
    }
    println("")
    barcosJugador(rs.numCols,rs.playerGrid,rs.computerGrid,rs.playerShips)

}

fun barcosJugador(nCols: Int,pGrid:Array<Array<String?>>,cGrid:Array<Array<String?>>,pShips:Int){

    println("Coloca tus barcos:")


}