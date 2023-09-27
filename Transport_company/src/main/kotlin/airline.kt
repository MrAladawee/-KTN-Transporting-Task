class Airline () : Transport {

    override var price = 3000 // за 1км
    override var speed = 900 // км ч
    override var maxWeight = 20000
    override var accident: Double
        get() = 0.1
        set(value) {}
}