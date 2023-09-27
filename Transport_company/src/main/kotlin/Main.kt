val car = Car() ; val airline = Airline() ; val train = Train()
val arrTransport = arrayOf(car,airline,train)

fun main(args: Array<String>) {

    var A = City("A")
    var B = City("B")
    var C = City("C")
    var D = City("D")
    var DictionaryCities = mapOf(A to 0, B to 1, C to 2, D to 3)

    var CitiesTable_Temple1 = Distance(A,B,C,D)
    CitiesTable_Temple1.createDist()
    CitiesTable_Temple1.createHighway()
    CitiesTable_Temple1.createBigCities()

    var consumer_1 =  Consumer(1, 1700, A,B, wPrice = 2000000, wTime = 30.0)
    var consumer_2 =  Consumer(1, 1700, A,B, wPrice = 200, wTime = 30.0)

    var TC = TransportCompany(CitiesTable_Temple1, DictionaryCities)
    TC.resultWay(consumer_1) ; println()
    TC.resultWay(consumer_2)

}