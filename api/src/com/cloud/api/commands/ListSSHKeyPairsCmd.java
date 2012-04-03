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
import com.cloud.api.BaseCmd.CommandType;
import com.cloud.api.BaseListProjectAndAccountResourcesCmd;
import com.cloud.api.Implementation;
import com.cloud.api.Parameter;
import com.cloud.api.response.ListResponse;
import com.cloud.api.response.SSHKeyPairResponse;
import com.cloud.user.SSHKeyPair;

@Implementation(description="List registered keypairs", responseObject=SSHKeyPairResponse.class) 
public class ListSSHKeyPairsCmd extends BaseListProjectAndAccountResourcesCmd {
    public static final Logger s_logger = Logger.getLogger(ListSSHKeyPairsCmd.class.getName());
    private static final String s_name = "listsshkeypairsresponse";
    
    
    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////
	
	@Parameter(name=ApiConstants.NAME, type=CommandType.STRING, description="A key pair name to look for") 
	private String name;
	
    @Parameter(name="fingerprint", type=CommandType.STRING, description="A public key fingerprint to look for") 
    private String fingerprint;

    
    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    ///////////////////////////////////////////////////// 
    
	public String getName() {
		return name;
	}
	
	public String getFingerprint() {
		return fingerprint;
	}
    
    
    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////
    
	@Override
	public void execute() {
		List<? extends SSHKeyPair> resultList = _mgr.listSSHKeyPairs(this);
		List<SSHKeyPairResponse> responses = new ArrayList<SSHKeyPairResponse>();
		for (SSHKeyPair result : resultList) {
			SSHKeyPairResponse r = new SSHKeyPairResponse(result.getName(), result.getFingerprint());
			r.setObjectName("sshkeypair");
			responses.add(r);
		}
		
        ListResponse<SSHKeyPairResponse> response = new ListResponse<SSHKeyPairResponse>();
        response.setResponses(responses);
        response.setResponseName(getCommandName());
        this.setResponseObject(response);
	}

	@Override
	public String getCommandName() {
		return s_name;
	}

}
