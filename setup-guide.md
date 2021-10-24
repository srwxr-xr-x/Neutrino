## Quick Setup

If you merely wish to build the mod from source, or make small personal changes to the code, then the project can be built with minimal setup:
- Ensure a [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) is installed on your computer. (Java 16 is needed)
- Copy the code by pressing the 'clone or download' button and then 'Download ZIP'. Unzip the downloaded zip to any directory on your computer you like.
    - or optionally use version control (see full setup for details).
- Open a command prompt from the project's root folder and type `./gradlew runClient` to run the game.
- Type `./gradlew build` to build a release JAR file, which will be placed in `/build/libs`.

Any code changes you make will be applied when you newly run or compile the game.

## Full Setup

Performing a full setup is strongly recommended if you wish to make changes to the code beyond very minor personal modifications.

### Prerequisites

To compile Neutrino for desktop using this guide you will need:
- A computer which meets the system requirements for [Intellij IDEA](https://www.jetbrains.com/help/idea/installation-guide.html)
- (optional) a GitHub account to fork this repository, if you wish to use version control

### Installing programs

Download and install the latest version of [Intellij](https://www.jetbrains.com/idea/download). This software includes all the tools you will need to compile the game.

Intellij is recommended over VSCode or others as it is commonly used, if you prefer VSCode and are certain you do not need help, you may use that instead. This guide will assume Intellij is used, but the two programs are very similar.

It is optional, but strongly recommended, to use version control to manage your copy of the Neutrino codebase. Version control is software which helps you manage changes to code. To use version control you will need to download and install [Git](https://git-scm.com/downloads). You are welcome to use a separate graphical git client or git CLI if you prefer, but this guide will use Intellij's built-in git tools.

### Setting up your copy of the code

If you are using version control, fork this repository using the 'fork' button at the top-right of this web page, so that you have your own copy of the code on GitHub.

If you do not wish to use version control, press the 'clone or download' button and then 'Download ZIP'. Unzip the downloaded zip to any directory on your computer you like.

### Opening the code in Intellij
Go to the directory where you cloned the repository, with Github Desktop this will be in Documents/Github/Neutrino on Windows unless you specify elsewhere

Right click the `build.gradle` file and hit Open with..

Select Intellij IDEA

### Running the code

Once the code is open, you can run it from Intellij by specifying the gradle command from the quick guide as a run configuration:
- Select 'Run' on the top toolbar, and then 'Edit Configurations...'.
- Click the + icon to add a new configuration, and select 'gradle'.
- Set 'Gradle project' to 'Neutrino', and 'tasks' to 'runClient'
- Name the configuration whatever you like, and select 'Apply' and 'OK' on the bottom right.

That configuration is now saved and can be selected from the configurations dropdown menu. It can be ran using the green arrow icon, and debugged with the green bug icon.

### Generating a distributable JAR file

A JAR (Java ARchive) is a file used to distribute Java applications. Just as with running the code, a run configuration must be set up to create the jar. Follow the above steps, but with 'build' instead of 'runClient'. Running this configuration will generate a distributable JAR file in the `/build/libs` folder.

Note that by distributing your modification of Neutrino, you are bound by the terms of the GPLv3 license, which requires that you make any modifications you have made open-source. If you followed this guide and are using version control, that is already set up for you as your forked repository is publicly hosted on GitHub. Just make sure to push any changes you make back to that repository.

Note that JAR files will require a [Java runtime enviroment](https://java.com/en/download/win10.jsp) to be installed on a user's computer in order to work.
