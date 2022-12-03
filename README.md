## **A Conway Game**

![Screenshot 1](demo.png)

This is a Java (Swing) implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) with support for:
- 6 seed styles (Random, Horizontal Line, Gliders, etc.)
- 4 sizes (Full Screen, Large, Medium, Small)
- Generation lengths from 50ms to 1s
- Configurable live cell color

### **Updates**

This was one of my first projects.  I came back about 4 years later with the idea that I would rewrite this in another language.  I did proof of concept versions in Scheme, Python, and Tcl before giving in and overhauling this version.  The overhaul included three new seed styles, two more size options, and a lot of refactoring.

I have ideas for where this could go from here, but it will have to wait again.

### **Build & Run**

The application is built with Maven:

```
git clone https://github.com/scottmcleodjr/conway-game.git
cd conway-game
mvn package
java -jar target/conway-1.2.0.jar
```

### **Credits**

Thanks and acknowledgements to jpetrich for review and testing.