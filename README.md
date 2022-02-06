All the commands mentioned here can and should be run from the project root folder.

Build:
- Add the path to the `rt.jar` on your machine in the `instrumenter.InstrumentMethod.java` class by initializing the `pathToRtJar` variable to it;
- `mvn package -Dmaven.test.skip=true`; (if you run just `mvn package` it won't work because the test suite needs the application .jar, which you are generating here);
  
Info:
- Demo classes containing methods you can instrument are in `test/java/demo.classes`;
- The test suite is in `test/java/instrumenter`;
- Each test in the test suite, instruments a method in a class and runs the instrumented class;
- So far, each class in the test suite only contains one method, in order to make it easy to run the instrumented method as a standalone;
- The tests succeed if there are no errors/exceptions in the instrumentation or in the run of the instrumented classes, they don't check for anything else;

Test:
- `mvn test`

If you want to do it manually:

Instrument:
- `java -cp target/freud-java-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar:target/classes:target/test-classes instrumenter.InstrumentMethod instrumenter.<name-of-class-you-wish-to-instrument> <name-of-method-you-wish-to-instrument>`;
  
Run Instrumented Class (and see the results in the console):
- `cp target/classes/instrumenter/MyTimer.class sootOutput/instrumenter/`;
- `java -cp sootOutput demo.classes.<name-of-instrumented-class>`.

Alternatively, you can also just run the `RunIntstrumentedMethod` class, passing as parameter the `<name-of-class-you-wish-to-instrument>`.

Non-mantained repository with additional work: https://gitlab.software.imdea.org/nicola.amadio/freud-java-instrumentation

This repository here was branched out from above one at commit 9e9c30c9bb4af99a4446e11ad518be51390403df.

# freud-java-instrumenter
