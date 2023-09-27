class City(var name : String) {

    var weather = weatherCreate()

    fun weatherCreate() : Boolean{
        var tmpRnd = (0..1).random()

        when (tmpRnd) {
            0 -> return false
            else -> return true
        }
    }

}