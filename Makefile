build:
	mvn -f tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider.application.parent clean initialize package install deploy docker:build -DskipTests 
push:
	docker push mbloomfi/zipcodeexample
   
 