#!/usr/bin/env bash
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



 

# $Id: ipassoc.sh 9804 2010-06-22 18:36:49Z alex $ $HeadURL: svn://svn.lab.vmops.com/repos/vmdev/java/scripts/network/domr/ipassoc.sh $
# ipassoc.sh -- associate/disassociate a public ip with an instance
# 2.1.4
usage() {
  printf "Usage:\n %s -A  -i <domR eth1 ip>  -l <public-ip-address>  -r <domr name> [-f] \n" $(basename $0) >&2
  printf " %s -D -i <domR eth1 ip> -l <public-ip-address> -r <domr name> [-f] \n" $(basename $0) >&2
}

cert="/root/.ssh/id_rsa.cloud"
domRIp=$1
shift


check_gw() {
  ping -c 1 -n -q $1 > /dev/null
  if [ $? -gt 0 ]
  then
    sleep 1
    ping -c 1 -n -q $1 > /dev/null
  fi
  return $?;
}


check_gw "$domRIp"
if [ $? -gt 0 ]
then
  exit 1
fi


ssh -p 3922 -q -o StrictHostKeyChecking=no -i $cert root@$domRIp "/root/ipassoc.sh $*"
exit $?

