jMTPe64
=======

Extended version of JMTP (https://code.google.com/p/jmtp/ (offline), copy https://github.com/Kurukshetran/jmtp) that adds the ability to copy files from the device to the host.

Extended version of JMTPe (https://github.com/ultrah/jMTPe) that adds a 64bit dll.

Compile
=======
- get ant https://ant.apache.org/bindownload.cgi
- get msbuild https://www.microsoft.com/de-de/download/details.aspx?id=48159
- add `ant` and `msbuild` to your PATH
- (optinal) get https://www.visualstudio.com/thank-you-downloading-visual-studio/?sku=Community&rel=15 to modify the build scripts
- check `JAVA_HOME`
- fix `VCTargetsPath` http://stackoverflow.com/questions/16092169 (set the ENV worked for me)
- change to `cd source` and call `ant`
