# Botlin: Kotlin enhancements for FRC
Simple syntax sugar and enhancements for Kotlin users of WPIlib

Online installation coming soon, for now follow the local build instructions

## Features
Easy-to-use trigger operations
```kt
val a = Trigger { /*...*/ }
val b = Trigger { /*...*/ }
val c = a and b
val d = !a or b
val e = c xnor d
```

Advanced unit literals
```kt
val weight = 123.Kilograms
val kV = 12.Volts/Meter
val batteryPercentageLose = 1.Percent per Minute
val consumption = 123.Watt * Minutes
```

And more to come!

## Building locally
If you want to contribute to the project, you need to build it locally. 

Run `./gradlew publishToMavinLocal` to build the library. 

For use in a project, copy the library JSON file into the `vendordeps` folder, and change the maven URL to: 
- MACOS: `/Users/<name>/.m2/repository` (`~/.m2/repository`)
- LINUX: `/home/<name>/.m2/repository` (`~/.m2/repository`)
- WINDOWS: `C:\Users\<name>\.m2\repository`