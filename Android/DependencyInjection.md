# Dependency Injection

Programming pattern 

Benefits:

- Easier for testing - you can mock the dependencies
- Reusability of code - Use different instances of a class as a dependency e.g. circle, square...
- Easier to refactor

Two main ways:

1. Constructor Injection
2. Field Injection (or Setter Injection)

## No Dependency Injection

Dependencies are instantiated within the class. 

In the example `Meal` class is tightly coupled with the `instructions` for `Spaghetti`...I hope you like a lot of spaghetti 🍝 because no subclasses or alternative implementations can easily be used. It's also going to be harder to test because you have to test with the real implementation of Spaghetti and Ingredient (can't use a test double e.g. mock).

```kt

class Meal {
  private val instructions = Spaghetti() 
  private val ingredients = listOf(Pasta(), Garlic(), Passata(), Basil())
  
  fun cook(id: String) {...}
}
```

```kt
fun main() {
  val meal = Meal()
  meal.cook("SPAG01234")
```

## Manual Dependency Injection using Constructor Injection

Pass objects as a parameter in the classes constructor. 

Cons:

- Lots of boilerplate code
  - In multi-layered architecture if you want to create a top layer object you have to provide dependencies for all layers below it. Want to create a meal you'll have to create the ingredients, instructions, utensils, cooking equipment etc.


```kt
class Meal(
  private val instructions : Instructions
  private val ingredients : List<Ingredient>
){
  fun cook(id: String) {...}
}
```
```kt
fun main() {
  private val spaghettiInstructions = Spaghetti() 
  private val spaghettiIngredients = listOf(Pasta(), Garlic(), Passata(), Basil())
  
  val meal = Meal(
    instructions = spaghettiInstructions,
    ingredients = spaghettiIngredients
  )
  meal.cook("SPAG01234")
}  
```

## Dependency Injection Libraries

Recommended to reduce the amount of boilerplate code required to get dependency injection working. Some Libraries:

- Dagger
- Guice
- Hilt (built on top of Dagger)
