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

public interface ExternalDhcpEntryListener {
	public class DhcpEntryState {
		String _name;
		
		public static final DhcpEntryState add = new DhcpEntryState("add");
		public static final DhcpEntryState old = new DhcpEntryState("old");
		public static final DhcpEntryState del = new DhcpEntryState("del");
		
		public DhcpEntryState(String name) {
			_name = name;
		}
		
		public String getName() {
			return _name;
		}
	}
	
	/**
	 * Notify that DHCP entry state change
	 * @param ip
	 * @param mac
	 * @param DHCP entry state
	 * @return: true means continuous listen on the entry, false cancels the listener
	 */
	public boolean notify(String ip, String mac, DhcpEntryState state, Object userData);
}
