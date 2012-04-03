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
package com.cloud.storage;

import java.net.URI;

import com.cloud.storage.Storage.ImageFormat;
import com.cloud.utils.component.Adapter;

public interface SecondaryStorageLayer extends Adapter {
    
    /**
     * Mounts a template
     * 
     * @param poolId the pool to mount it to.
     * @param poolUuid the pool's uuid if it is needed.
     * @param name unique name to the template.
     * @param url url to access the template.
     * @param format format of the template.
     * @param accountId account id the template belongs to.
     * @return a String that unique identifies the reference the template once it is mounted.
     */
    String mountTemplate(long poolId, String poolUuid, String name, URI url, ImageFormat format, long accountId);
    
}
