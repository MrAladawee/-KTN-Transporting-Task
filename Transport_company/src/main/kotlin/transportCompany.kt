import kotlin.math.min

class TransportCompany(var CitiesTable : Distance, var DictionaryCities : Map<City, Int>) {

    fun CreateWay(consumer : Consumer, CitiesTable : Distance, DictionaryCities : Map<City, Int>) : Array<Int> {

        val idCity_1 = DictionaryCities[consumer.City1]!!
        val idCity_2 = DictionaryCities[consumer.City2]!!

        val distance = CitiesTable.arrDistance[idCity_1][idCity_2]
        val typeCity1 = CitiesTable.arrBigCities[idCity_1][idCity_2]
        val typeCity2 = CitiesTable.arrBigCities[idCity_2][idCity_1]
        val highway = CitiesTable.arrHighway[idCity_1][idCity_2]
        val weather_1 = consumer.City1.weather
        val weather_2 = consumer.City2.weather

        val wTime = consumer.wTime ; val wPrice = consumer.wPrice ; val wWeight = consumer.wWeight

        val way_car = CreateWayUnoptimal(0, distance, typeCity1, typeCity2, highway, weather_1, weather_2) // cost for car
        val way_train = CreateWayUnoptimal(1, distance, typeCity1, typeCity2, highway, weather_1, weather_2) // cost for train
        val way_airplane = CreateWayUnoptimal(2, distance, typeCity1, typeCity2, highway, weather_1, weather_2) // cost for airplane
        // (Double, Double) : 1 - Cost , 2 - Time

        val way_car_cost = way_car[0].toInt() ; val way_car_time = way_car[1]
        val way_train_cost = way_train[0].toInt() ; val way_train_time = way_train[1]
        val way_airplane_cost = way_airplane[0].toInt() ; val way_airplane_time = way_airplane[1]

        val arrCost = arrayOf(way_car_cost, way_train_cost, way_airplane_cost)
        val arrTime = arrayOf(way_car_time, way_train_time, way_airplane_time)

        var arrCostTemp = arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)
        var arrTimeTemp = arrayOf(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)

        if (wPrice < 0 && wTime < 0) {
            var tempCost : Int = Int.MAX_VALUE
            var type : Int = 0
            for (i in 0..arrCost.size-1) {
                if (arrTransport[i].maxWeight > wWeight) {
                    tempCost = min(tempCost, arrCost[i])
                    type = i
                }
                else continue
            }
            return arrayOf( tempCost, type )
        }

        else if (wPrice > 0 && wTime < 0) {

            for (i in 0..arrCost.size-1) {
                if (arrCost[i] < wPrice && arrTransport[i].maxWeight > wWeight) arrCostTemp[i] = arrCost[i]
            }

            return arrayOf( arrCostTemp.min(), arrCostTemp.indexOf(arrCostTemp.min()) )

        }

        else if (wPrice < 0 && wTime > 0) {

            // Работаем со временем и коррелируем со стоимостью
            for (i in 0..arrTime.size-1) {
                if (arrTime[i] < wTime && arrTransport[i].maxWeight > wWeight) {arrTimeTemp[i] = arrTime[i] ; arrCostTemp[i] = arrCost[i]}
            }

            return arrayOf( arrCostTemp.min(), arrCostTemp.indexOf(arrCostTemp.min()) )

        }

        else if (wPrice > 0 && wTime > 0) {

            for (i in 0..arrTime.size-1) {
                if ((arrTime[i] < wTime) && (arrCost[i] < wPrice) && (arrTransport[i].maxWeight > wWeight)) {arrTimeTemp[i] = arrTime[i] ; arrCostTemp[i] = arrCost[i] }
            }

            return arrayOf( arrCostTemp.min(), arrCostTemp.indexOf(arrCostTemp.min()) )

        }

        return arrayOf(-1,-1)

    }

    fun CreateWayUnoptimal(type : Int /* 0 - car, 1 - train, 2 - airplane */,
                           distance : Int, typeCity1 : Int, typeCity2 : Int /* 0 - small, 1 - medium, 2 - big */,
                           highway : Int, weather_1 : Boolean, weather_2 : Boolean) : Array<Double>{

        if (type == 0) {
            if (highway == 1) return arrayOf((distance * car.price).toDouble(), distance / (car.speed * car.speedUp))
            else return arrayOf((distance * car.price).toDouble(), (distance / car.speed).toDouble())
        }

        else if (type == 1) {
            if (((typeCity1 == 1) || (typeCity1 == 2)) && ((typeCity2 == 1) || (typeCity2 == 2)) ){
                return arrayOf(distance * train.price.toDouble(), distance / train.speed.toDouble())
            }
            else {
                return arrayOf(Double.MAX_VALUE, Double.MAX_VALUE)
            }
        }

        else {
            if ((typeCity1 == typeCity2) && (typeCity1 == 2)) {
                if (weather_1 == weather_2 && weather_1 == true) {
                    return arrayOf(distance * airline.price.toDouble(), distance / airline.speed.toDouble())
                }
                else {
                    return arrayOf(Double.MAX_VALUE, Double.MAX_VALUE)
                }
            }
            else {
                return arrayOf(Double.MAX_VALUE, Double.MAX_VALUE)
            }
        }

    }

    fun resultWay(consumer : Consumer) {

        var cost = CreateWay(consumer,CitiesTable,DictionaryCities)[0]
        var type = CreateWay(consumer,CitiesTable,DictionaryCities)[1]

        if (cost < Int.MAX_VALUE) {

            var rndResult = (0 .. 10).random()
            if (arrTransport[type].accident*10 < rndResult) {

                println("Стоимость перевозки - $cost руб. через транспорт - " +
                        when (type) {
                            0 -> "автомобиль."
                            1 -> "поезд."
                            2 -> "самолет."
                            else -> ""
                        } +
                        "\nПогода из города в город: ${consumer.City1.weather}:${CitiesTable.arrBigCities[1][0]} -> ${consumer.City1.weather}:${CitiesTable.arrBigCities[0][1]}.")
                println("Проблемы на дороге - не обнаружены."
                )
            }

            else {
                println("Случилась авария во время транспортировки. Возмещена компенсация в размере ${cost*2} рублей.")
            }
        }

        else {
            println("Заказ не был взят.")
        }

    }

}