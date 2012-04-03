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
package com.cloud.async;

import java.util.List;

import com.cloud.utils.component.Manager;

public interface SyncQueueManager extends Manager {
    public SyncQueueVO queue(String syncObjType, long syncObjId, String itemType, long itemId);
    public SyncQueueItemVO dequeueFromOne(long queueId, Long msid);
    public List<SyncQueueItemVO> dequeueFromAny(Long msid, int maxItems);
    public void purgeItem(long queueItemId);
    public void returnItem(long queueItemId);
    
	public List<SyncQueueItemVO> getActiveQueueItems(Long msid, boolean exclusive);
    public List<SyncQueueItemVO> getBlockedQueueItems(long thresholdMs, boolean exclusive);
	public void resetQueueProcess(long msid);
}
