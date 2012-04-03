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

import org.apache.log4j.Logger;

import com.cloud.api.ApiConstants;
import com.cloud.api.BaseAsyncCmd;
import com.cloud.api.BaseCmd;
import com.cloud.api.IdentityMapper;
import com.cloud.api.Implementation;
import com.cloud.api.Parameter;
import com.cloud.api.ServerApiException;
import com.cloud.api.response.UserVmResponse;
import com.cloud.async.AsyncJob;
import com.cloud.event.EventTypes;
import com.cloud.exception.ConcurrentOperationException;
import com.cloud.hypervisor.Hypervisor.HypervisorType;
import com.cloud.user.Account;
import com.cloud.user.UserContext;
import com.cloud.uservm.UserVm;

@Implementation(responseObject = UserVmResponse.class, description = "Stops a virtual machine.")
public class StopVMCmd extends BaseAsyncCmd {
    public static final Logger s_logger = Logger.getLogger(StopVMCmd.class.getName());

    private static final String s_name = "stopvirtualmachineresponse";

    // ///////////////////////////////////////////////////
    // ////////////// API parameters /////////////////////
    // ///////////////////////////////////////////////////

    @IdentityMapper(entityTableName = "vm_instance")
    @Parameter(name = ApiConstants.ID, type = CommandType.LONG, required = true, description = "The ID of the virtual machine")
    private Long id;

    @Parameter(name = ApiConstants.FORCED, type = CommandType.BOOLEAN, required = false, description = "Force stop the VM.  The caller knows the VM is stopped.")
    private Boolean forced;

    // ///////////////////////////////////////////////////
    // ///////////////// Accessors ///////////////////////
    // ///////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    // ///////////////////////////////////////////////////
    // ///////////// API Implementation///////////////////
    // ///////////////////////////////////////////////////

    @Override
    public String getCommandName() {
        return s_name;
    }

    public static String getResultObjectName() {
        return "virtualmachine";
    }

    @Override
    public long getEntityOwnerId() {
        UserVm vm = _responseGenerator.findUserVmById(getId());
        if (vm != null) {
            return vm.getAccountId();
        }

        return Account.ACCOUNT_ID_SYSTEM; // no account info given, parent this command to SYSTEM so ERROR events are
// tracked
    }

    @Override
    public String getEventType() {
        return EventTypes.EVENT_VM_STOP;
    }

    @Override
    public String getEventDescription() {
        return "stopping user vm: " + getId();
    }

    @Override
    public AsyncJob.Type getInstanceType() {
        return AsyncJob.Type.VirtualMachine;
    }

    @Override
    public Long getInstanceId() {
        return getId();
    }

    public boolean isForced() {
        return (forced != null) ? forced : false;
    }

    @Override
    public void execute() throws ServerApiException, ConcurrentOperationException {
        UserContext.current().setEventDetails("Vm Id: " + getId());
        UserVm result;

        if (_userVmService.getHypervisorTypeOfUserVM(getId()) == HypervisorType.BareMetal) {
            result = _bareMetalVmService.stopVirtualMachine(getId(), isForced());
        } else {
            result = _userVmService.stopVirtualMachine(getId(), isForced());
        }

        if (result != null) {
            UserVmResponse response = _responseGenerator.createUserVmResponse("virtualmachine", result).get(0);
            response.setResponseName(getCommandName());
            this.setResponseObject(response);
        } else {
            throw new ServerApiException(BaseCmd.INTERNAL_ERROR, "Failed to stop vm");
        }
    }
}
