# Botlin: Kotlin enhancements for FRC
Simple syntax sugar and enhancements for Kotlin users of WPIlib

## Install
IntelliJ & Offline: Copy paste `Botlin.json` into your vendordeps folder

VSCode: Open the command palette, run `WPILib: Manage Vendor Libraries`, 
select `Install new libraries (online)`, 
then input `https://raw.githubusercontent.com/Kotlin-for-FRC/Botlin/refs/heads/main/Botlin.json`

## Features
### - Advanced unit literals
```kt
val weight = 123.kilograms
val kV = 12.volts/meter
val batteryPercentageLose = 1.percent per minute
val consumption = 123.watt * minutes
val acceleration = 1.meter per seconds.squared
```

### - Easy-to-use trigger operations
```kt
val a = Trigger { /*...*/ }
val b = Trigger { /*...*/ }
val c = a and b
val d = !a or b
val e = c xnor d
// !, and, or, xor, nand, nor, xnor 
```

### - Delegated command builders 
```kt
val myCommand by buildSequentialCommandDelegate {
    requires(MySubsystem)
    
    runCommand(MySubsystem.command1)
    
    waitTime(500.milliseconds)
    
    runWithTimeout(1.5.seconds) {
        runCommand(MySubsystem.command2)
        runCommand(MySubsystem.command3)
    }
}
val myOtherCommand by buildFunctionalCommandDelegate {
    requires(MySubsystem)
    start { 
        println("Starting") 
    }
    execute { 
        println("Executing") 
    }
    end { 
        interrupted -> 
        println("Ended. Interrupted: $interrupted") 
    }
    isFinished { MySubsystem.isCommandFinished }
}
```

### - Coroutine commands (using  WPILib 2026!)
```kt
val myCORCommand by coroutineCommandDelegate {
    var x = 0
    while(x < 10) {
        println("COR command 1: $x")
        wait(500.milliseconds)
        x++
    }
}
val my2ndCORCommand = CoroutineCommand(requirements = setOf(MySubsystem)) {
    var y = 0
    while(y < 10) {
        println("COR command 2: $y")
        wait(500.milliseconds)
        if(y == 5) {
            await(myCORCommand)
        }
        y++
    }
}
/* Features:
 * - yield(): single iteration
 * - waitUntil(condition): wait until condition is true
 * - waitWhile(condition): wait while condition is true
 * - wait(time): wait an amount of time
 * - await(command): schedule and wait for another command
 */
```

And more to come!

## Building locally
If you want to contribute to the project, you need to build it locally. 

Run `./gradlew publishToMavinLocal` to build the library. 

For use in a project, copy the library JSON file into the `vendordeps` folder, and change the maven URL to: 
- MACOS: `file:///Users/<name>/.m2/repository`
- LINUX: `file:///home/<name>/.m2/repository`
- WINDOWS: `file:///C:/Users/<name>/.m2/repository`