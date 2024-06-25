# java-kata-moba
Public repo for our teams practicing of the Java Kata for mob programming and our Assignment 2 for ESEP.

## Tooling
We are going to use Intellij - Code With Me. This will allow us all to have the project up and in front of us, so that anyone can take over with coding on a moments need and we don't have to worry about passing controls akwardly. It will also help us all effectively mob in the same style as pair programming would have you do. Additionally, we can all navigate the exact same in-progress code freely. There are also some really nice tools for following other devs and it has cool change tracking features for individual contributors.

TODO -
1. Add a bunch of unit tests and get excellent code coverage.
2.
## Mob Programming Video

Youtube Link: https://youtu.be/FYtm7q0LMRo

# From the Kata's README:
# Gilded Rose starting position in Java


## Run the TextTest Fixture from Command-Line

```
./gradlew -q text
```

### Specify Number of Days

For e.g. 10 days:

```
./gradlew -q text --args 10
```

You should make sure the gradle commands shown above work when you execute them in a terminal before trying to use TextTest (see below).


## Run the TextTest approval test that comes with this project

There are instructions in the [TextTest Readme](../texttests/README.md) for setting up TextTest. What's unusual for the Java version is there are two executables listed in [config.gr](../texttests/config.gr) for Java. The first uses Gradle wrapped in a python script. Uncomment these lines to use it:

    executable:${TEXTTEST_HOME}/Java/texttest_rig.py
    interpreter:python

The other relies on your CLASSPATH being set correctly in [environment.gr](../texttests/environment.gr). Uncomment these lines to use it instead:

    executable:com.gildedrose.TexttestFixture
    interpreter:java
