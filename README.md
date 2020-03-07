## (Another Version of) Conway's Game of Life

Conway's Game of Life is a no-player game (effectively an animation) that proceeds from a starting layout or 'seed' according to pre-determined rules.

More information about the game can be found [here](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

#### Setup

The program only requires a recent(ish) version of Java and will be able to run on any platform.

You'll need to...

1) Clone the repo: `git clone https://gitlab.com/s.mcleodjr/conway-game.git`
2) Open the repo: `cd conway-game`
3) Compile and create a jar file: `mvn package`
4) Run the program: `java -jar target/conway-1.0-SNAPSHOT.jar`

### Configuration Options

###### Current

The initial configuration prompt provides options for the starting style (a random pattern, a horizontal line, or a box), full screen animation, the speed of animation (ranging from 25 to 925 ms), and the color of 'live' pixels.

###### Future

Potential future options for development include:
* More starting styles, including symmetrical random.
* Control of cell size (> 1px X 1px).

### Credits
Thanks and acknowledgments to jpetrich for review and testing.
