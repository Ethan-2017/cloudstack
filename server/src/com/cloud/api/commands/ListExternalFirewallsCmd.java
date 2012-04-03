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
package com.cloud.api.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cloud.api.ApiConstants;
import com.cloud.api.BaseListCmd;
import com.cloud.api.IdentityMapper;
import com.cloud.api.Implementation;
import com.cloud.api.Parameter;
import com.cloud.api.PlugService;
import com.cloud.api.response.ListResponse;
import com.cloud.host.Host;
import com.cloud.network.element.JuniperSRXFirewallElementService;
import com.cloud.server.api.response.ExternalFirewallResponse;

@Implementation(description="List external firewall appliances.", responseObject = ExternalFirewallResponse.class)
public class ListExternalFirewallsCmd extends BaseListCmd {
	public static final Logger s_logger = Logger.getLogger(ListServiceOfferingsCmd.class.getName());
    private static final String s_name = "listexternalfirewallsresponse";

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @IdentityMapper(entityTableName="data_center")
    @Parameter(name=ApiConstants.ZONE_ID, type=CommandType.LONG, required = true, description="zone Id")
    private long zoneId;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public long getZoneId() {
        return zoneId;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    @PlugService JuniperSRXFirewallElementService _srxElementService;

    @Override
    public String getCommandName() {
        return s_name;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void execute(){

    	List<? extends Host> externalFirewalls = _srxElementService.listExternalFirewalls(this);

        ListResponse<ExternalFirewallResponse> listResponse = new ListResponse<ExternalFirewallResponse>();
        List<ExternalFirewallResponse> responses = new ArrayList<ExternalFirewallResponse>();
        for (Host externalFirewall : externalFirewalls) {
        	ExternalFirewallResponse response = _srxElementService.createExternalFirewallResponse(externalFirewall);
        	response.setObjectName("externalfirewall");
        	response.setResponseName(getCommandName());
        	responses.add(response);
        }

        listResponse.setResponses(responses);
        listResponse.setResponseName(getCommandName());
        this.setResponseObject(listResponse);
    }
}
