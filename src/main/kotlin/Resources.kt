data class Resources(
    var numRows: Int,
    var numCols: Int,
    var playerShips: Int,
    var computerShips : Int

){

    var playerGrid: Array<Array<String?>> = Array(numRows) { arrayOfNulls<String>(numCols) }
    var computerGrid: Array<Array<String?>> = Array(numRows) { arrayOfNulls<String>(numCols) }
    val missedGuesses: Array<Array<Int?>> = Array(numRows) { arrayOfNulls<Int>(numCols) }
}
