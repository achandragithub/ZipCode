build:
	mvn -f tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider.application.parent clean initialize package install deploy docker:build -DskipTests 
push:
	docker push mbloomfi/zipcodeexample
 
test-sonar-local:
	sonar-scanner -Dsonar.projectKey=zipcode-example -Dsonar.projectVersion=1.0 -Dsonar.sources=tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider -Dsonar.host.url=http://192.168.76.20:9000  -Dsonar.login=353e5aa90f8926b540ca205561de76a52d78deef -Dsonar.java.binaries=tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider.application/target -Dsonar.sonar.exclusions=./test-data 
	
sonar-scanner -Dsonar.projectKey=zipcode-example -Dsonar.projectVersion=1.0 -Dsonar.sources=. -Dsonar.host.url=http://192.168.76.20:9000  -Dsonar.login=353e5aa90f8926b540ca205561de76a52d78deef -Dsonar.java.binaries=../tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider.application/target -Dsonar.sonar.exclusions=./test-data 
   
