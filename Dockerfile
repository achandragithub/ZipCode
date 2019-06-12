FROM harbor-test.tibcopsg.net/bwce/bwce-base:2.4.4
LABEL maintainer TIBCO Software Inc.

COPY tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider.application/target/tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider.application_1.0.0.ear  /

EXPOSE 8080
EXPOSE 7777
