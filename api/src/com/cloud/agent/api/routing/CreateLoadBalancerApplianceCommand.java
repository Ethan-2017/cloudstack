// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/02/2012
package com.cloud.agent.api.routing;

/** NetworkElementCommand to spin a load balancer appliance */

public class CreateLoadBalancerApplianceCommand extends NetworkElementCommand {

    String ip;
    String netmask;
    String gateway;
    String username;
    String password;
    String publicInterface;
    String privateInterface;
    
    public CreateLoadBalancerApplianceCommand(String ip, String netmask, String gateway) {
        this.ip = ip;
        this.netmask = netmask;
        this.gateway = gateway;
    }

    public String getLoadBalancerIP() {
        return ip;
    }

    public String getNetmask() {
        return netmask;
    }

    public String getGateway() {
        return gateway;
    }
}