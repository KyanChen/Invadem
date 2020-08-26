# Invadem Game Description

**Invadem** is a simple shoot'em up game where the player controls a tank that fights off a horde of invading space ships. The program use ``gradle`` to build and run, and use the `processing` java library.

When the game starts, you can choose the number of players:

<img src="https://i.loli.net/2020/08/08/Mysozu5ixDRtlPw.png" width="500">

Then you can see the main game screen: 

<img src='https://i.loli.net/2020/08/08/ce5ZuOmWJNTLIjX.png' width=500>

For player 1, you can use `space bar`, `left key`, `right key` to shoot and move the tank.

For player 2, you can use `w`, `a`, `d` to shoot and move the tank.

# Build and Run

## Install Gradle

This program uses [Gradle](https://gradle.org/install/) to build and run.

But the gradle installed in some ways may not work for this program. 

Please follow the instructions below to build and run this program successfully.

### Linux & MacOS

#### Install sdkman

[**SDKMAN!**](https://sdkman.io/install) is a tool for managing parallel versions of multiple **Software Development Kits** on most Unix based systems.

You can run following command to install it:

Simply open a new terminal and enter:

```bash
$ curl -s "https://get.sdkman.io" | bash
```

Follow the instructions on-screen to complete installation.
Next, open a new terminal **or** enter:

```bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
```

Lastly, run the following code snippet to ensure that installation succeeded:

```bash
$ sdk version
```

If all went well, the version should be displayed. Something like:

```bash
$ sdkman 5.0.0+51
```

Then we can install the **gradle**:

```bash
# intall the gradle
$ sdk install gradle

# install specific java version 
$ sdk install java 8.0.262-zulu

# tell sdkman to use a specific version
$ sdk use java 8.0.262-zulu
```

### Windows

Install [chocolatey](https://chocolatey.org/) package manager

Please make sure you follow a set of instructions that work for your system here https://chocolatey.org/install

Afterwards, you can install gradle

```bash
$ choco install gradle
```

> I didn't test this program on the Windows

## Build and Run

Now you can use gradle to build and run this program.

To build the program you can run

```bash
$ gradle build
```

This way the gradle will run the tests automatically (the program will play the game itself), which may take minutes to run.

To aovid this, you can run

```bash
$ gradle build -x test
```

After that, we can run the program

```bash
$ gradle run
```

# Game Objects

The project contains a number of entities that will need to be modelled within your application. 

The images of these objects are in the folder [`src/main/resources`](src/main/resources).

**Tank**

This is a player controlled entity that can be moved by pressing the left and right arrow keys on the keyboard and moves at a rate of 1 pixel per a frame . The tank is 22x14 pixels, it starts at the bottom-middle of the display. A tank can fire projectiles which can hit the barriers or enemy invaders. It can shoot multiple projectiles towards the invaders. If an enemy projectile hits the tank, it will lose a hit point, if the tank is hit 3 times, the game should transition to a **Game Over** screen since the tank has been destroyed.

**Invader**

Each invader has a unique starting position but move in time with every other invader. The invader swarm moves from the top-middle of the screen to the player's barriers. Once an invader has reached the barriers, the game should transition to a **Game Over** screen.

Invaders will move 30 steps in one direction before moving down and heading 30 steps in the other direction. Each sideways step will constitute a movement of 1 pixel, each step is made every two frames. When an invader moves downward, it will move 8 pixels down and transition to it's other sprite.

The invaders are part of a larger swarm, the swarm starts with 40 invaders (10 invaders per row, 4 rows). Each invader is 16x16 pixels, this will correspond with the size of their sprites. They will have the same collision area as their sprites.

Once an invader is hit, it is considered to have been destroyed and should no longer be rendered by the game. When all invaders have been hit, this will result in the player winning the game and transitioning to the **Next Level** screen.

Every 5 seconds, an invader will be randomly selected to fire a projectile downwards.

**Barrier**

A barrier is composed of 7 different components, each component can sustain 3 hits. Once a component has been destroyed, it no longer offers protection for the tank. When a barrier sustains a hit, it will change to a different sprite, indicating that it has been damaged. The player is provided with 3 barriers, each barrier, left barrier is at least 20 pixels away from the left boundary described in the application section, the center barrier starts in the centre of the screen, right barrier is at least 20 pixels away from the right boundary. Each barrier is at least 10 pixels above the tank's location.

A barrier can be hit by the tank and an invader.

**Projectile**

A projectile can be fired by both the tank and an invader, however, an invader's projectile will not hit any other invader (only the barrier and tank). The tank can hit the barrier as well as any invader. Once a projectile impacts with another entity, it will cease to exist.

The projectile is 1x3 pixels and travels upwards 1px per frame.

# Game Conditions

The goal of the game is for the player to destroy all invaders before either the tank is destroyed or the invaders land.

- The player wins when the following conditions have been met: All invaders are destroyed.

- The computer wins when one of the following conditions have been met:

- An invader reaches the barriers (10px away from the barriers). The tank is hit 3 times and destroyed.

# UML

![](https://i.loli.net/2020/08/26/uRH417A5XhSwnI6.png)

The structure of the program is shown above.

`App` is the entry of the program, which inherits from `PApplet`.

All other classes inherited from `AbstractObject` except for `Barrier`.

`Barrier` is a single class. The barrier is composed of different blocks, which inherits from `AbstractBlock`.

Other relationships are obvious to understand from the UML graph.

# Extension

This part elaborates on the extension in addition to the requirement in the specification.

## Hearts

To display the hit points of the tank visibly, I created a class named `Heart` extending `AbstractObject`. It has 2 sprites, a whole heart and a broken heart.

## More Information Displayed

The game time of the current round, level and the heart of tanks are displayed to the players.

## Double Players Mode

To make the game can be played by two players, I changed the `Tank` to `AbstractTank` and created two sub classes named `Tank1` and `Tank2`, storing different sprites. And use an array of length 2 storing 2 `AbstractTank`objects. Besides, the game asks the player to select the number of players at the beginning of the game, which overrides the `mouseClicked` method.

