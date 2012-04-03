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
package com.cloud.agent.api.storage;

import com.cloud.agent.api.Command;

public class CreateEntityDownloadURLCommand extends AbstractDownloadCommand {

    public CreateEntityDownloadURLCommand(String parent, String installPath, String uuid) { // this constructor is for creating template download url
        super();
        this.parent = parent; // parent is required as not the template can be child of one of many parents
        this.installPath = installPath;
        this.extractLinkUUID = uuid;
    }
    
    public CreateEntityDownloadURLCommand(String installPath, String uuid) {
        super();
        this.parent = parent;
        this.installPath = installPath;
        this.extractLinkUUID = uuid;
    }

    public CreateEntityDownloadURLCommand() {
    }

    private String installPath;
    private String parent;
    private String extractLinkUUID;
    
    @Override
    public boolean executeInSequence() {
        return false;
    }

    public String getInstallPath() {
        return installPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }
    
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

	public String getExtractLinkUUID() {
		return extractLinkUUID;
	}

	public void setExtractLinkUUID(String extractLinkUUID) {
		this.extractLinkUUID = extractLinkUUID;
	}

}
