package com.epam.task1

/**
 * Add implementation for Zoo class by following points:
 * 1) Constructor should take two arguments int and double.
 *      1.1) Int represents quantity limit of animals that can be added to the Zoo
 *      1.2) Double represents total limit of weight for Zoo, so total weight of animals in Zoo cannot exceed this value
 * 2) Implement function create. Add checks for animalsLimit and weightLimit so they should be greater than 0.
 *  In case condition violations throw IllegalArgumentException
 * 3) Implement operators to add and remove animals.
 *      3.1) The operators should work with assignments. So next code should compile zoo += Animal(...), zoo -= Animal().
 *      3.2) Also implement -= operator in such a way that it can work with both Int value and Animal object.
 *          For case with Animal remove object from the Zoo, for case with Int remove Animal from the Zoo by index.
 *          If index is out of bounds throw IllegalArgumentException
 *      3.3) For add operator check that it is possible to add new Animal to the Zoo and all condtions will be met by count and by weight of the Zoo.
 *          Also, it should not be possible to add Animal with negative weight. All the cases should throw IllegalArgumentException.
 * 4) Implement couple invoke operators:
 *      4.1) Invoke without arguments should iterate through all animals and print following string
 *          to the console using 'println' method "AnimalName fed completed" where AnimalName is value from name property in Animal object.
 *      4.2) Invoke with double argument should iterate through Animals and print to console Animal name
 *          in case weight of the animal is greater than argument provided in function
 */
class Zoo {
    companion object {
        fun create(animalsLimit: Int, weightLimit: Double): Zoo {
            TODO("Create Zoo object")
        }
    }

    fun getAllAnimals(): List<Animal> = TODO("Return animals in the Zoo")
}