class Car () : Transport {

    override var price = 1000 // за 1км
    override var speed = 70 // км ч
    override var maxWeight = 4000
    override var speedUp: Double
        get() = 1.6
        set(value) {}
    override var accident: Double
        get() = 0.4
        set(value) {}
}