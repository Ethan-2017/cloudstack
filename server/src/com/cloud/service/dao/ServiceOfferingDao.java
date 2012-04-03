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
package com.cloud.service.dao;

import java.util.List;

import com.cloud.service.ServiceOfferingVO;
import com.cloud.utils.db.GenericDao;

/*
 * Data Access Object for service_offering table
 */
public interface ServiceOfferingDao extends GenericDao<ServiceOfferingVO, Long> {
    ServiceOfferingVO findByName(String name);
    ServiceOfferingVO persistSystemServiceOffering(ServiceOfferingVO vo);
	List<ServiceOfferingVO> findPublicServiceOfferings();
	List<ServiceOfferingVO> findServiceOfferingByDomainId(Long domainId);
    List<ServiceOfferingVO> findSystemOffering(Long domainId, Boolean isSystem, String vm_type);
    ServiceOfferingVO persistDeafultServiceOffering(ServiceOfferingVO offering);
}
