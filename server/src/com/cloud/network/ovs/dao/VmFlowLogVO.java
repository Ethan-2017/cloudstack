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
package com.cloud.network.ovs.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cloud.utils.db.GenericDao;

@Entity
@Table(name="ovs_vm_flow_log")
public class VmFlowLogVO {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="instance_id", updatable=false, nullable=false)
    private Long instanceId;    // vm_instance id
    
    @Column(name=GenericDao.CREATED_COLUMN)
    private Date created;
    
    @Column(name="logsequence")
    long logsequence;
    
    @Column(name="vm_name", updatable=false, nullable=false, length=255)
	protected String name = null;
    
    protected VmFlowLogVO() {
    	
    }

	public VmFlowLogVO(Long instanceId, String name) {
		super();
		this.instanceId = instanceId;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public Long getInstanceId() {
		return instanceId;
	}

	public Date getCreated() {
		return created;
	}

	public long getLogsequence() {
		return logsequence;
	}
    
	public void incrLogsequence() {
		logsequence++;
	}
	
	public String getName() {
		return name;
	}
}
