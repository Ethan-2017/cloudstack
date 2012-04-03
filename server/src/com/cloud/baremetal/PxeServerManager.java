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
package com.cloud.baremetal;

import com.cloud.deploy.DeployDestination;
import com.cloud.host.Host;
import com.cloud.host.HostVO;
import com.cloud.uservm.UserVm;
import com.cloud.utils.component.Manager;
import com.cloud.vm.ReservationContext;
import com.cloud.vm.UserVmVO;
import com.cloud.vm.VirtualMachineProfile;

public interface PxeServerManager extends Manager {
	public static class PxeServerType {
		private String _name;
		
		public static final PxeServerType PING = new PxeServerType("PING");
		public static final PxeServerType DMCD = new PxeServerType("DMCD");
		
		public PxeServerType(String name) {
			_name = name;
		}
		
		public String getName() {
			return _name;
		}
		
	}

	public PxeServerResponse getApiResponse(Host pxeServer);
	
	public boolean prepare(PxeServerType type, VirtualMachineProfile<UserVmVO> profile, DeployDestination dest, ReservationContext context, Long pxeServerId);

	Host addPxeServer(PxeServerProfile profile);
	
	public boolean prepareCreateTemplate(PxeServerType type, Long pxeServerId, UserVm vm, String templateUrl);
	
	public PxeServerType getPxeServerType(HostVO host);
}
