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
package com.cloud.network.lb;

import java.util.List;

import com.cloud.network.rules.FirewallRule;
import com.cloud.network.rules.LoadBalancer;
import com.cloud.utils.Pair;

public class LoadBalancingRule implements FirewallRule, LoadBalancer{
    private LoadBalancer lb;
    private List<LbDestination> destinations;
    private List<LbStickinessPolicy> stickinessPolicies;
    
    public LoadBalancingRule(LoadBalancer lb, List<LbDestination> destinations, List<LbStickinessPolicy> stickinessPolicies) { 
        this.lb = lb;
        this.destinations = destinations;
        this.stickinessPolicies = stickinessPolicies;
    }
    
    @Override
    public long getId() {
        return lb.getId();
    }
    
    @Override
    public long getAccountId() {
        return lb.getAccountId();
    }
    
    @Override
    public long getDomainId() {
        return lb.getDomainId();
    }
    
    @Override
    public String getName() {
        return lb.getName();
    }
    
    @Override
    public String getDescription() {
        return lb.getDescription();
    }

    public int getDefaultPortStart() {
        return lb.getDefaultPortStart();
    }
    
    @Override
    public int getDefaultPortEnd() {
        return lb.getDefaultPortEnd();
    }

    @Override
    public String getAlgorithm() {
        return lb.getAlgorithm();
    }
    
    @Override
    public String getXid() {
        return lb.getXid();
    }
    
    @Override
    public long getSourceIpAddressId() {
        return lb.getSourceIpAddressId();
    }
    
    @Override
    public Integer getSourcePortStart() {
        return lb.getSourcePortStart();
    }
    
    @Override
    public Integer getSourcePortEnd() {
        return lb.getSourcePortEnd();
    }

    @Override
    public String getProtocol() {
        return lb.getProtocol();
    }
    
    @Override
    public Purpose getPurpose() {
        return Purpose.LoadBalancing;
    }
    
    @Override
    public State getState() {
        return lb.getState();
    }
    
    @Override
    public long getNetworkId() {
        return lb.getNetworkId();
    }
    
    public LoadBalancer getLb() {
        return lb;
    }

    public List<LbDestination> getDestinations() {
        return destinations;
    }
    
    public List<LbStickinessPolicy> getStickinessPolicies() {
        return stickinessPolicies;
    }
    
    
    public interface Destination {
        String getIpAddress();
        int getDestinationPortStart();
        int getDestinationPortEnd();
        boolean isRevoked();
    }

    public static class LbStickinessPolicy {
        private String _methodName;
        private List<Pair<String, String>> _params;
        private boolean _revoke;

        public LbStickinessPolicy(String methodName, List<Pair<String, String>> params, boolean revoke) {
            this._methodName = methodName;
            this._params = params;
            this._revoke = revoke;
        }

        public LbStickinessPolicy(String methodName, List<Pair<String, String>> params) {
            this._methodName = methodName;
            this._params = params;
            this._revoke = false;
        }
        
        public String getMethodName() {
            return _methodName;
        }

        public List<Pair<String, String>> getParams() {
            return _params;
        }

        public boolean isRevoked() {
            return _revoke;
        }
    }
    
    public static class LbDestination implements Destination {
        private int portStart;
        private int portEnd;
        private String ip;
        boolean revoked;
        
        public LbDestination(int portStart, int portEnd, String ip, boolean revoked) {
            this.portStart = portStart;
            this.portEnd = portEnd;
            this.ip = ip;
            this.revoked = revoked;
        }
        
        public String getIpAddress() {
            return ip;
        }
        public int getDestinationPortStart() {
            return portStart;
        }
        public int getDestinationPortEnd() {
            return portEnd;
        }
        
        public boolean isRevoked() {
            return revoked;
        }
       
        public void setRevoked(boolean revoked) {
            this.revoked = revoked;
        }
    }
    
    @Override
    public Integer getIcmpCode() {
        return null;
    }
    
    @Override
    public Integer getIcmpType() {
        return null;
    }
    
    @Override
    public List<String> getSourceCidrList() {
        return null;
    }
    
    @Override
    public Long getRelated() {
        return null;
    }

	@Override
	public FirewallRuleType getType() {
		return FirewallRuleType.User;
	}
}
