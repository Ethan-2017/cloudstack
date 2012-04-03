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

 

x=$1
y=$2
name=$3

pod_query="GET  http://10.91.30.219:8096/client/?command=createPod&zoneId=1&name=SSP$name&cidr=182.$x.$y.0%2F24&startIp=182.$x.$y.2&endIp=182.$x.$y.252&gateway=182.$x.$y.1
HTTP/1.0\n\n"

echo -e $pod_query | nc -v -q 20 10.91.30.219 8096

#vlan_query="GET http://10.91.30.219/client/?command=createVlanIpRange&vlan=untagged&zoneid=1&podId=$name&forVirtualNetwork=false&gateway=172.$y.$x.1&netmask=255.255.255.0&startip=172.$y.$x.2&endip=172.$y.$x.252        HTTP/1.0\n\n"

#echo -e $vlan_query | nc -v -q 20 10.91.30.219 8096
