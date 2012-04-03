#!/bin/sh
# Copyright 2012 Citrix Systems, Inc. Licensed under the
# Apache License, Version 2.0 (the "License"); you may not use this
# file except in compliance with the License.  Citrix Systems, Inc.
# reserves all rights not expressly granted by the License.
# You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# 
# Automatically generated by addcopyright.py at 04/02/2012

# wec
export CATALINA_HOME=${HOME}/automated
# macos tomcat
#ORIG_TOMCAT=/usr/local/tomcat
# linux/jenkins host tomcat
ORIG_TOMCAT=/usr/share/tomcat6

mkdir_copy_files() {
    if [ -z "$1" ]; then
	return 0
    fi

    echo "Copying $1 files to $2..."

    mkdir -p $2
    cp -R $1/* $2
    return $?
}

if [ ! -d ${ORIG_TOMCAT} ]; then
    echo "Tomcat must be installed on this system"
    exit 1
fi

if [ -d ${CATALINA_HOME} ]; then
    echo "Existing test Tomcat exists!!!"
    exit 1
fi

# now let's copy over the required files...
mkdir_copy_files ${ORIG_TOMCAT}/conf ${CATALINA_HOME}/conf
mkdir_copy_files ${ORIG_TOMCAT}/bin ${CATALINA_HOME}/bin
mkdir_copy_files ${ORIG_TOMCAT}/lib ${CATALINA_HOME}/lib
mkdir_copy_files ${ORIG_TOMCAT}/logs ${CATALINA_HOME}/logs
mkdir_copy_files ${ORIG_TOMCAT}/temp ${CATALINA_HOME}/temp
mkdir_copy_files ${ORIG_TOMCAT}/webapps ${CATALINA_HOME}/webapps
mkdir_copy_files ${ORIG_TOMCAT}/work ${CATALINA_HOME}/work

ant clean-all

ant automated-test-run

# clean up our temp tomcat!
rm -rf ${CATALINA_HOME}

exit $?
