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
(function(cloudStack) {
  cloudStack.sections['global-settings'] = {
    title: 'label.menu.global.settings',
    id: 'global-settings',
    sectionSelect: {
      label: 'label.select-view'
    },
    sections: {
      globalSettings: {
        type: 'select',
        title: 'label.menu.global.settings',
        listView: {
          label: 'label.menu.global.settings',
          actions: {
            edit: {
              label: 'label.change.value',
              action: function(args) {           
                var name = args.data.jsonObj.name;
                var value = args.data.value;

                $.ajax({
                  url: createURL(
                    'updateConfiguration&name=' + name + '&value=' + value
                  ),
                  dataType: 'json',
                  async: true,
                  success: function(json) {                
                    var item = json.updateconfigurationresponse.configuration;
                    cloudStack.dialog.notice({ message: _l('message.restart.mgmt.server') });
                    args.response.success({data: item});
                  },
                  error: function(json) {                
                    args.response.error(parseXMLHttpResponse(json));
                  }
                });
              }
            }
          },
          fields: {
            name: { label: 'label.name', id: true },
            description: { label: 'label.description' },
            value: { label: 'label.value', editable: true }
          },
          dataProvider: function(args) {
            var data = {
              page: args.page,
              pagesize: pageSize
            };

            if (args.filterBy.search.value) {
              data.name = args.filterBy.search.value;
            }

            $.ajax({
              url: createURL('listConfigurations'),
              data: data,
              dataType: "json",
              async: true,
              success: function(json) {
                var items = json.listconfigurationsresponse.configuration;
                args.response.success({ data: items });
              }
            });
          }
        }
      },
      hypervisorCapabilities: {
        type: 'select',
        title: 'label.hypervisor.capabilities',
        listView: {
          id: 'hypervisorCapabilities',
          label: 'label.hypervisor.capabilities',
          fields: {
            hypervisor: { label: 'label.hypervisor' },
            hypervisorversion: { label: 'label.hypervisor.version' },
            maxguestslimit: { label: 'label.max.guest.limit' }
          },
          dataProvider: function(args) {					  
						var array1 = [];  
						if(args.filterBy != null) {          
							if(args.filterBy.search != null && args.filterBy.search.by != null && args.filterBy.search.value != null) {
								switch(args.filterBy.search.by) {
								case "name":
									if(args.filterBy.search.value.length > 0)
										array1.push("&keyword=" + args.filterBy.search.value);
									break;
								}
							}
						}				
					  
            $.ajax({
              url: createURL("listHypervisorCapabilities&page=" + args.page + "&pagesize=" + pageSize + array1.join("")),
              dataType: "json",
              async: true,
              success: function(json) {
                var items = json.listhypervisorcapabilitiesresponse.hypervisorCapabilities;
                args.response.success({data:items});
              },
              error: function(data) {
                args.response.error(parseXMLHttpResponse(data));
              }
            });
          },

          detailView: {
            name: 'label.details',
            actions: {
              edit: {
                label: 'label.edit',
                action: function(args) {
                  var array1 = [];
                  array1.push("&maxguestslimit=" + todb(args.data.maxguestslimit));
                  $.ajax({
                    url: createURL("updateHypervisorCapabilities&id=" + args.context.hypervisorCapabilities[0].id + array1.join("")),
                    dataType: "json",
                    success: function(json) {
                      var item = json.updatehypervisorcapabilitiesresponse['null'];
                      args.response.success({data: item});
                    },
                    error: function(data) {
                      args.response.error(parseXMLHttpResponse(data));
                    }
                  });
                }
              }
            },

            tabs: {
              details: {
                title: 'label.details',
                fields: [
                  {
                    id: { label: 'label.id' },
                    hypervisor: { label: 'label.hypervisor' },
                    hypervisorversion: { label: 'label.hypervisor.version' },
                    maxguestslimit: {
                      label: 'label.max.guest.limit',
                      isEditable: true
                    }
                  }
                ],
                dataProvider: function(args) {
                  args.response.success(
                    {
                      data:args.context.hypervisorCapabilities[0]
                    }
                  );
                }
              }
            }
          }
        }
      }
    }
  };
})(cloudStack);
