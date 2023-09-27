class Train () : Transport {

    override var price = 400 // за 1км
    override var speed = 85 // км ч
    override var maxWeight = 66000
    override var accident: Double
        get() = 0.3
        set(value) {}
}