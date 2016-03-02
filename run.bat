E:
cd E:\webmagic\webmagic-core
call mvn clean
call mvn test
call mvn package
call mvn install
call mvn cobertura:cobertura
cd E:\webmagic\webmagic-extension
call mvn clean
call mvn test
call mvn package
call mvn install
call mvn cobertura:cobertura
cd E:\webmagic\webmagic-quartz
call mvn clean
call mvn test
call mvn package
call mvn install
call mvn cobertura:cobertura
cd E:\webmagic\webmagic-samples
call mvn clean
call mvn test
call mvn package
call mvn install
call mvn cobertura:cobertura
cd E:\webmagic\webmagic-trial
call mvn clean
call mvn test
call mvn package
call mvn install
call mvn cobertura:cobertura
pause