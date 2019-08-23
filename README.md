jMTPe64
=======

Extended version of JMTP (https://code.google.com/p/jmtp/ (offline), copy https://github.com/Kurukshetran/jmtp) that adds the ability to copy files from the device to the host.

Extended version of JMTPe (https://github.com/ultrah/jMTPe) that adds a 64bit dll.

Refactored version of JMTPe64 (https://github.com/mheinzerling/jMTPe) with Maven and maven NAR plugin.
Tiny refactor with comme interface PortableDeviceContainer between Folder and Storage

Compile
=======
- get Maven
- get `https://github.com/AlainCo/nar-maven-plugin` Patch of maven-nar-plugin to manage the toopathsdk on windows10... deploy it as snapshot (until change is integrated)
- get MSVC++14 as MS C++ studio community... not easy to find it
- set `JAVA_HOME` and MSVC variables as proposed in `/jMTPe/jmtpe-dll/src/main/doc/msvc14` . if problems appears, look at environment variable in the pom.xml of jmtpe-dll
-  call mvn install

Problems and alternatives
=========================

- no way to move or rename objects
- no set of attributes

Investigating if com4j is not a better alternative, but there is problems too.
Looking at Com4JNA too, but not finished either.