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

 

name=$1

host_query="GET	http://10.91.30.226:8096/client/?command=addHost&zoneId=1&podId=$((name+250))&username=sim&password=sim&clustername=simulator-$name&hosttags=RP$name&url=http%3A%2F%2Fsim	HTTP/1.0\n\n"
echo -e $host_query | nc -v -q 60 10.91.30.226 8096
