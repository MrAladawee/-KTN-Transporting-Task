class Distance (vararg ArrayCities : City) {

    val rows = ArrayCities.size
    val cols = ArrayCities.size

    var arrDistance = Array(rows) { IntArray(cols) {0} }
    var arrHighway = Array(rows) { IntArray(cols) {0} }
    var arrBigCities = Array(rows) { IntArray(cols) {0} }


    fun createHighway() {
        for (i in 0..rows-1) {
            for (j in 0..cols-1) {

                if (i == j) arrHighway[i][j] = 0
                else if (j > i) arrHighway[i][j] = (0..1).random()
                else if (j < i) arrHighway[i][j] = arrHighway[j][i]

            }
        }
    }
    fun showHighway() {
        for (row in arrHighway) {
            println(row.contentToString())
        }
    }

    fun createDist() {
        for (i in 0..rows-1) {
            for (j in 0..cols-1) {

                if (i == j) arrDistance[i][j] = 0
                else if (j > i) arrDistance[i][j] = (100..2000).random()
                else if (j < i) arrDistance[i][j] = arrDistance[j][i]

            }
        }
    }
    fun showTable() {
        for (row in arrDistance) {
            println(row.contentToString())
        }
    }

    fun createBigCities() {
        for (i in 0..rows-1) {
            for (j in 0..cols-1) {

                if (i == j) arrBigCities[i][j] = 0
                else if (j > i) arrBigCities[i][j] = (0..2).random()
                else if (j < i) arrBigCities[i][j] = arrBigCities[j][i]

            }
        }
    }
    fun showBigCities() {
        for (row in arrBigCities) {
            println(row.contentToString())
        }
    }

}