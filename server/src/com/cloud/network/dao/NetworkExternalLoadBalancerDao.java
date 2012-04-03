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
package com.cloud.network.dao;

import java.util.List;

import com.cloud.network.NetworkExternalLoadBalancerVO;
import com.cloud.utils.db.GenericDao;

public interface  NetworkExternalLoadBalancerDao extends GenericDao<NetworkExternalLoadBalancerVO, Long> {

    /**
     * find the network to load balancer device mapping corresponding to a network
     * @param networkId guest network Id
     * @return return NetworkExternalLoadBalancerVO for the guest network
     */
    NetworkExternalLoadBalancerVO findByNetworkId(long networkId);

    /**
     * list all network to load balancer device mappings corresponding to a load balancer device Id
     * @param lbDeviceId load balancer device Id
     * @return list of NetworkExternalLoadBalancerVO mappings corresponding to the networks mapped to the load balancer device 
     */
    List<NetworkExternalLoadBalancerVO> listByLoadBalancerDeviceId(long lbDeviceId);
}
