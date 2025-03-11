fun defaultExample(arg1:String = "Hi") {
    println(arg1)
}

fun requiredExample(name: String, date: String, food: String = "burger") {
    println("$name on $date ate $food")

}

class Box(val length: Int, val width: Int = 20, val height: Int = 40)

fun main() {
    val box2 = Box(length = 200)
    // eager: occurs regardless of whether the result is ever used
    // lazy; occurs only if necessary at runtime

    val ints = listOf(-3,-2,-1,1,2,3)
    println(ints.filter { it > 0 })
    println(ints.filter { n: Int -> n > 0 })
    // lambda is an expression that makes a function that has no name
    /*
    var dirtLevel = 20
    var waterFilter = { level: Int -> level / 2 }
    print(waterFilter(dirtLevel))
    */

    /*
    defaultExample()
    defaultExample("helloooo")*/
    //for more clarity -> defaultExample(arg1 = "helloooo")

    //requiredExample("William", "1/27")
    //requiredExample("William", "1/28","pizza")

    /*
    var temperature = 20
    var isHot = if(temperature > 40) true else false
    println(isHot)
    temperature = 50
    println(isHot)

    --- returns false for both
     */
    println("Hello World!")
}

fun fillerFun(args: Array<String>) {

}
fun returnFun(name: String?): Unit {
    println("Hi there!")
}
fun returnFun2(name: String?) {
    println("Hi there!")
}
/*
fun double(x: Int): Int {
    return x * 2
}*/
fun double(x: Int): Int = x * 2

