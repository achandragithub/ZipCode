FROM nexus.tibcopsg.net:18079/bwce/bwce-base:latest
MAINTAINER TIBCO Software Inc.

ADD tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider.application/tibco.bwce.sample.binding.soap.http.ZipCodeServiceProvider.application_1.0.0.ear /

EXPOSE 8080