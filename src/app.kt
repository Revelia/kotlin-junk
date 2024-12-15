import java.util.concurrent.atomic.AtomicLong
import kotlin.math.sqrt


// Hello World



fun main(args: Array<String>) { helloWorld() }


const val name = "Human in global"
// camel case is standard
fun helloWorld() {
    val name = "Human in main"
    println("Hello, $name!")
    println("Hello " + name + "!")
    println("Hello ${name}!")
    println("Hello \${name}!")
    println("Hello ${name}! ${countVowelsInString(name)}")
    println(printProduct(44))
    println(Binom(2,1,-3).roots)
    doSomeStuffWithPersons()
}

// Property:

class DummyString(str: String) {
    val str: String = str
    var num: Int = 0

    val length: Int
        get() = str.length

    var number: Int
        get() = num
        set(value) {
            num = value
        }

}

fun countVowelsInString(str: String): Int {
    val vowelsSet = "aeiouy".toSet()
    return str.filter { c -> c in vowelsSet }.length
}

fun printProduct(n: Long) {
    if (n == 1L) {
        println("1")
    }
    else {
        printProduct(n, from=2)
    }
}

fun printProduct(n: Long, from: Long) {
    (from .. n).forEach{ i ->
        when {
            n % i == 0L -> {
                print("$i ")
                printProduct(n / i, from=i)
                return
            }
            i * i > n -> {
                print("$n ")
                return
            }
        }
    }
}


class Binom(a: Int, b: Int, c: Int) {
    val a = a.toDouble()
    val b = b.toDouble()
    val c = c.toDouble()
    private val d: Double
        get(){
            return sqrt( this.b * this.b - 4* this.a * this.c )
        }

    private val q = 2 * a

    val roots: Pair<Double,Double>
        get(){
            return Pair((-this.b + this.d) / q, (-this.b - this.d) / q)
        }
}

class Point(x_: Double, y_: Double) {
    private val xCounter = AtomicLong()
    private val yCounter = AtomicLong()
    val x: Double = x_
        get() {
            xCounter.incrementAndGet()
            return field // Возвращаем field чтобы не было рекурсии при get x
        }
}


// Dataclasses

class PersonOrdinary(val name: String, val age: Int) {
    val a = 5
}


data class PersonData(val name: String, val age: Int)

fun doSomeStuffWithPersons() {
    var person1 = PersonOrdinary(name = "Alice", age = 5)
    var person1_data: PersonData = PersonData(name = "Alice", age = 5)
    var person2 = PersonOrdinary(name = "Alice", age = 6)
    var person2_data: PersonData = PersonData(name = "Alice", age = 6)
    var person3 = PersonOrdinary(name = "Alice", age = 5)
    var person3_data: PersonData = PersonData(name = "Alice", age = 5)
    println(person1 == person2) // false
    println(person1 == person3) // false
    println(person1_data == person2_data) // false
    println(person1_data == person3_data) // true
    var person4data = person1_data.copy(age = 12)
    print(person4data) // PersonData(name=Alice, age=12)
}

