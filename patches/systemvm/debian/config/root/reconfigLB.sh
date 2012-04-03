#!/bin/bash
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




ret=0
# save previous state
  mv /etc/haproxy/haproxy.cfg /etc/haproxy/haproxy.cfg.old
  mv /var/run/haproxy.pid /var/run/haproxy.pid.old

  mv /etc/haproxy/haproxy.cfg.new /etc/haproxy/haproxy.cfg
  kill -TTOU $(cat /var/run/haproxy.pid.old)
  sleep 2
  if haproxy -D -p /var/run/haproxy.pid -f /etc/haproxy/haproxy.cfg; then
    logger -t cloud "New haproxy instance successfully loaded, stopping previous one."
    kill -KILL $(cat /var/run/haproxy.pid.old)
    rm -f /var/run/haproxy.pid.old
    ret=0
  else
    logger -t cloud "New instance failed to start, resuming previous one."
    kill -TTIN $(cat /var/run/haproxy.pid.old)
    rm -f /var/run/haproxy.pid
    mv /var/run/haproxy.pid.old /var/run/haproxy.pid
    mv /etc/haproxy/haproxy.cfg /etc/haproxy/haproxy.cfg.new
    mv /etc/haproxy/haproxy.cfg.old /etc/haproxy/haproxy.cfg
    ret=1
  fi

exit $ret

